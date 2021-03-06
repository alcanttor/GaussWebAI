import {
  Component,
  OnInit,
  ElementRef,
  ViewChild,
  ViewChildren,
  QueryList,
} from '@angular/core';
import { EmailTemplatesService } from './email-templates.service';
import { EmailLabelsService } from '../email-labels/email-labels.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {
  ToolbarService,
  LinkService,
  ImageService,
  HtmlEditorService,
} from '@syncfusion/ej2-angular-richtexteditor';
import {
  SortableHeader,
  SortEvent,
  compare,
} from '../shared/directives/SortableHeader';

@Component({
  selector: 'app-email-templates',
  templateUrl: './email-templates.component.html',
  styleUrls: ['./email-templates.component.sass'],
  providers: [ToolbarService, LinkService, ImageService, HtmlEditorService],
})
export class EmailTemplatesComponent implements OnInit {
  public templates = [];
  public sortedTemplates = [];
  myForm: FormGroup;
  public template = {
    id: null,
    name: '',
    template: '',
    labels: [],
  };
  public value = '';
  public labels = [];
  public labelId = '';
  public labelInRouter = '';
  @ViewChild('createTemplate') createTemplate: ElementRef;
  @ViewChildren(SortableHeader) headers: QueryList<SortableHeader>;
  constructor(
    private templatesService: EmailTemplatesService,
    private modalService: NgbModal,
    private labelsService: EmailLabelsService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.labelsService.getLabels().subscribe((data: any) => {
      this.labels = data;
    });

    this.route.queryParams.subscribe((qps) => {
      this.labelId = qps.labelId;
      this.labelInRouter = qps.labelName;
      this.getTemplates();
    });

    this.myForm = this.formBuilder.group({
      labels: [],
    });
  }

  dragStart(e, template) {
    e.dataTransfer.setData('templates', JSON.stringify([template]));
  }

  dragEnd(e) {
    e.preventDefault();
    this.getTemplates();
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting countries
    if (direction === '' || column === '') {
      this.sortedTemplates = this.templates;
    } else {
      this.sortedTemplates = [...this.templates].sort((a, b) => {
        const res = compare(`${a[column]}`, `${b[column]}`);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  getTemplates() {
    const labelId = this.labelId;
    this.templatesService.getTemplates(labelId).subscribe((data: any[]) => {
      this.templates = data;
      this.sortedTemplates = this.templates;
    });
  }

  listen($event) {
    if ($event.action === 'add') {
      this.openCreateModal(this.createTemplate);
    }
  }

  openDeleteModal(deleteLabel, template) {
    this.modalService
      .open(deleteLabel, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.templatesService.delete(template.id).subscribe(() => {
            this.getTemplates();
          });
        },
        (onrejected) => {}
      );
  }

  generateEmptyTemplate = () => ({
    id: null,
    name: '',
    labels: [],
    template: '',
  });

  openEditModal(createTemplate, template) {
    this.template = template;
    this.myForm.setValue({
      labels: this.template.labels.map((label) => label.id),
    });
    this.modalService
      .open(createTemplate, { ariaLabelledBy: 'modal-basic-title', size: 'xl' })
      .result.then(
        (onfulfilled) => {
          const labels = this.myForm.value.labels;
          this.template.labels = labels.map((label) => ({
            id: label,
          }));
          this.templatesService.updateTemplate(this.template).subscribe(() => {
            this.template = this.generateEmptyTemplate();
            this.getTemplates();
          });
        },
        (onrejected) => {
          this.template = this.generateEmptyTemplate();
        }
      );
  }

  openCreateModal(createTemplate) {
    this.modalService
      .open(createTemplate, { ariaLabelledBy: 'modal-basic-title', size: 'xl' })
      .result.then(
        (onfulfilled) => {
          const labels = this.myForm.value.labels || [];
          this.template.labels = labels.map((label) => ({
            id: label,
          }));
          this.templatesService.addTemplate(this.template).subscribe(() => {
            this.getTemplates();
          });
        },
        (onrejected) => {}
      );
  }
}

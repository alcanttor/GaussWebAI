import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { EmailLabelsService } from './email-labels.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-email-labels',
  templateUrl: './email-labels.component.html',
  styleUrls: ['./email-labels.component.sass'],
})
export class EmailLabelsComponent implements OnInit {
  public labels = [];
  public label = {
    id: null,
    name: '',
  };
  @ViewChild('createLabel') createLabel: ElementRef;
  constructor(
    private labelsService: EmailLabelsService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getLabels();
  }

  getLabels() {
    this.labelsService.getLabels().subscribe((data: any[]) => {
      this.labels = data;
      console.log(this.labels);
    });
  }

  generateEmptyLabel() {
    return {
      id: null,
      name: '',
    };
  }

  listen($event) {
    if ($event.action === 'add') {
      this.openCreateModal(this.createLabel);
    }
  }

  openCreateModal(createRule) {
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.labelsService.addLabel(this.label).subscribe(() => {
            this.label = this.generateEmptyLabel();
            this.getLabels();
          });
        },
        (onrejected) => {
          this.label = { id: null, name: '' };
        }
      );
  }

  openEditModal(createRule, label) {
    this.label = label;
    this.modalService
      .open(createRule, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.labelsService.updateLabel(this.label).subscribe(() => {
            this.label = this.generateEmptyLabel();
            this.getLabels();
          });
        },
        (onrejected) => {
          this.label = { id: null, name: '' };
        }
      );
  }

  openDeleteModal(deleteLabel, label) {
    this.modalService
      .open(deleteLabel, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (onfulfilled) => {
          this.labelsService.deleteLabel(label.id).subscribe(() => {
            this.getLabels();
          });
        },
        (onrejected) => {}
      );
  }
}

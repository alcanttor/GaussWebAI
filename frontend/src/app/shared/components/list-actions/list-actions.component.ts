import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-list-actions',
  templateUrl: './list-actions.component.html',
  styleUrls: ['./list-actions.component.sass'],
})
export class ListActionsComponent implements OnInit {
  @Input() showItemActions = false;
  @Output() messageEvent = new EventEmitter<object>();
  constructor() {}

  ngOnInit(): void {}

  emitEvent(action) {
    this.messageEvent.emit({
      action,
    });
  }
}

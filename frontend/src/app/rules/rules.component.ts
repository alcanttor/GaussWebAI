import { Component, OnInit } from '@angular/core';
import { Rule } from '../shared/models/rule';
import { RulesService } from './rules.service';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.sass'],
})
export class RulesComponent implements OnInit {
  public rules: Rule[] = [];
  constructor(private rulesService: RulesService) {}

  ngOnInit(): void {
    this.rulesService.getRules(1).subscribe((data: any[]) => {
      console.log(data);
      this.rules = data;
    });
  }

  listen($event) {
    console.log($event);
  }
}

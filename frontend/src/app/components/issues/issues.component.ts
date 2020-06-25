import { Component, OnInit } from '@angular/core';

import { Issue } from '../../interfaces/issue';
import { IssuesService } from '../../services/issues/issues.service'

@Component({
  selector: 'app-issues',
  templateUrl: './issues.component.html',
  styleUrls: ['./issues.component.css']
})
export class IssuesComponent implements OnInit {

  issues: Issue[];
  selectedIssue: Issue;

  constructor(private issuesService: IssuesService) { }

  ngOnInit(): void {
    this.issues = [];
    this.getIssues();
  }

  onSelect(issue: Issue): void {
    this.selectedIssue = issue;
  }

  getIssues(): void {
    this.issuesService.getIssues('angular/angular').
      subscribe(issues => this.issues = issues);
  }

}

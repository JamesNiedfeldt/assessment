import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Issue } from '../../interfaces/issue'

@Injectable({
  providedIn: 'root'
})
export class IssuesService {

  private baseUrl = 'https://api.github.com/repos/';

  constructor(private http: HttpClient) { }

  getIssues(repo: string): Observable<Issue[]> {
    let issues: Issue[] = [];

    //Build request parameters
    let date = new Date();
    date.setDate(date.getDate() - 7);
    let params = new HttpParams().set('since', date.toISOString());
    console.log(date.toISOString());
    
    let req = this.baseUrl + repo + '/issues';

    return of(this.makeRequest(issues, req, params))
      .pipe(catchError(this.handleError<Issue[]>('makeRequest', [])));
  }

  private makeRequest(issues: Issue[], url: string, params: HttpParams): Issue[] {
    this.http.get(url, {params: params, observe: 'response'}).subscribe(response => {
      let data = response.body
      let assignees: string[] = [];

      for (let i in data) {
        //Ignore pull requests
        if (!data[i].hasOwnProperty('pull_request')) {
          assignees = [];
          if (data[i]['assignee'] != null) {
            for (let a in data[i]['assignees']) {
              assignees.push(data[i]['assignees'][a]['login']);
            }
          } else {
            assignees.push('None');
          }
  
          //Add to list of issues
          issues.push({
            title: data[i]['title'],
            userName: data[i]['user']['login'],
            body: data[i]['body'],
            assignees: assignees,
            created: data[i]['created_at'],
            updated: data[i]['updated_at']
          });
        }
      }

      //Check if there are additional pages
      for (let l of response.headers.get('link').split(',')) {
        if (l.includes('rel="next"')) {
          let nextPage = l.substring(l.lastIndexOf('<') + 1, l.lastIndexOf('>'));
          issues.concat(this.makeRequest(issues, nextPage, null));
        }
      }
    });

    return issues;
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      
      return of(result as T);
    }
  }
}

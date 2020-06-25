/**
 * Interface representing a single issue object
 * 
 * @author James Niedfeldt
 */

export interface Issue {
    title: string;
    body: string;
    userName: string;
    assignees: string[];
    created: string;
    updated: string;
}
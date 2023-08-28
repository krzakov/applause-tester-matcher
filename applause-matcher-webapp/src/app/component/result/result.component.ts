import {Component, Input} from '@angular/core';
import {Tester} from "../../model/tester.interface";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})

export class ResultComponent {
  @Input() testers: Tester[] = [];
}

import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {SearchParams} from "./search-params";

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  @Input() countries: string[] = [];
  @Input() devices: string[] = [];

  @Output() search = new EventEmitter<SearchParams>();
  @Output() clear = new EventEmitter<void>();

  dropdownSettings: IDropdownSettings = {};

  selectedCountries: string[] = [];
  selectedDevices: string[] = [];

  ngOnInit() {
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'itemText',
      textField: 'itemText',
      selectAllText: 'select ALL',
      unSelectAllText: 'unselect ALL',
      itemsShowLimit: 6,
      allowSearchFilter: false
    };
  }

  findTesters() {
    const searchParams = {
      countries: this.selectedCountries,
      devices: this.selectedDevices
    };

    this.search.emit(searchParams);
  }

  clearResult() {
    this.clear.emit();
  }
}

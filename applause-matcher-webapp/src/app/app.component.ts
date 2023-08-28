import {Component, OnInit} from '@angular/core';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {DeviceService} from './device.service';
import {CountryService} from './country.service';
import {TestersService} from "./testers.service";
import {Tester} from "./tester.interface";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Applause Tester Matcher !';

  dropdownSettings: IDropdownSettings = {};

  devicesDropdownList: string[] = [];
  devicesSelectedItems: string[] = [];

  countriesDropdownList: string[] = [];
  countriesSelectedItems: string[] = [];

  testersResult: Tester[] = [];

  constructor(private deviceService: DeviceService,
              private countryService: CountryService,
              private testerService: TestersService) {
  }

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

    this.deviceService.getDevices().subscribe(devices => {
      this.devicesDropdownList = devices;
    });

    this.countryService.getCountries().subscribe(countries => {
      this.countriesDropdownList = countries;
    });
  }

  findTesters() {
    this.testerService.getTesters(this.countriesSelectedItems, this.devicesSelectedItems).subscribe(testers => {
      this.testersResult = testers;
    });
  }

  clearResult() {
    this.testersResult = [];
  }

  onSelectAllDevices(items: any) {
    this.devicesSelectedItems = [];
  }

  onSelectAllCountries(items: any) {
    this.countriesSelectedItems = [];
  }
}

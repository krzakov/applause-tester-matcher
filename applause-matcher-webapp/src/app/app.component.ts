import {Component, OnInit} from '@angular/core';
import {DeviceService} from './device.service';
import {CountryService} from './country.service';
import {TestersService} from "./testers.service";
import {Tester} from "./model/tester.interface";
import {SearchParams} from "./component/search-form/search-params";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Applause Tester Matcher !';

  devices: string[] = [];
  countries: string[] = [];
  testers: Tester[] = [];

  constructor(private deviceService: DeviceService,
              private countryService: CountryService,
              private testerService: TestersService) {
  }

  ngOnInit() {
    this.deviceService.getDevices().subscribe(devices => {
      this.devices = devices;
    });

    this.countryService.getCountries().subscribe(countries => {
      this.countries = countries;
    });
  }

  onSearch(event: SearchParams) {
    this.testerService.getTesters(event.countries, event.devices).subscribe(testers => {
      this.testers = testers;
    });
  }

  onClear() {
    this.testers = [];
  }
}

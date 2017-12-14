import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { ConfigService } from '@syndesis/ui/config.service';
import { log, getCategory } from '@syndesis/ui/logging';

import { ApiConnector, ApiConnectors, ApiConnectorStore as ApiStore } from './api-connector.models';

import {
  ActionConfig,
  ListConfig,
  EmptyStateConfig,
} from 'patternfly-ng';

import { Store } from '@ngrx/store';
import { PlatformStore, MetadataState } from '@syndesis/ui/platform';
import { ApiConnectorStore } from '@syndesis/ui/customizations/api-connector/api-connector.store';

@Component({
  selector: 'syndesis-api-connector-list',
  templateUrl: './api-connector-list.component.html',
  styleUrls: ['./api-connector-list.component.scss']
})
export class ApiConnectorListComponent implements OnInit {
  apiConnectors$: Observable<ApiConnectors>;
  filteredApiConnectors$ = new BehaviorSubject(<ApiConnectors>{});
  loading$: Observable<boolean>;
  listConfig: ListConfig;
  appName;
  itemUseMapping: { [valueComparator: string]: string } = {
    '=1': '<strong>1</strong> time',
    'other': '<strong>#</strong> times'
  };

  constructor(
    private store: Store<PlatformStore>,
    private apiStore: Store<ApiStore>,
    public config: ConfigService,
    private apiConnectorStore: ApiConnectorStore,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.apiConnectors$ = this.apiConnectorStore.list;
    this.loading$ = this.apiConnectorStore.loading;
    this.listConfig = {
      dblClick: false,
      multiSelect: false,
      selectItems: false,
      showCheckbox: false,
      emptyStateConfig: {
        iconStyleClass: 'pficon pficon-add-circle-o',
        title: 'Create API Connector',
        info:
          'There are currently no API connectors available. Please click on the button below to create one.',
        actions: {
          primaryActions: [
            {
              id: 'createApiConnector',
              title: 'Create API Connector',
              tooltip: 'Create API Connector'
            }
          ],
          moreActions: []
        } as ActionConfig
      } as EmptyStateConfig
    };
  }

  handleAction(event: any) {
    if (event.id === 'createApiConnector') {
      this.router.navigate(['create'], { relativeTo: this.route });
    }
  }

  handleClick(event: any) {
    const apiConnector = event.item;
    this.router.navigate([apiConnector.id], { relativeTo: this.route });
  }

  ngOnInit() {
    this.appName = this.config.getSettings('branding', 'appName', 'Syndesis');
    this.apiConnectorStore.loadAll();

    const apiConnectorState$ = this.apiStore.select('apiConnectorState');
    apiConnectorState$.subscribe(apiConnectorState => {
      console.log(apiConnectorState);
    });
    const metadataState$ = this.store.select('metadataState');
    metadataState$.subscribe(metadataState => {
      console.log(metadataState);
    });
  }
}

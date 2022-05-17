import { Injectable } from '@angular/core';
import endpointsConfig from './endpoints.json';
import config from '../../environments/config.json';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

endpointsObjConfig = endpointsConfig as { [key: string]: string };
apiUrl = config.apiUrl;

constructor() { }

getEndpoint(key: string, id: string | null = null): string | undefined {
  const keys = Object.keys(this.endpointsObjConfig);
  if (keys.includes(key)) {
    const endpoint = this.endpointsObjConfig[key];
    if (id !== null) {
      endpoint.replace('$', id);
    }
    return `${this.apiUrl}${endpoint}`;
  }
  return undefined;
}

}

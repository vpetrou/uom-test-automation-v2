/**
 * Class that contains consts for the Rest api and its resources
 */
export class Consts {
  /**
   * Calculates the Rest URL according to the domain that is deployed the UI
   * The Rest URL should be deployed in the same domain
   */
  // static readonly API_URL: string = window.location.protocol + '//' + window.location.host + '/sfd/sfd-ui-services-war/api/';
  // Prefix path for all the Rest Calls in the application (Use it only for development)
  static readonly  API_URL: string = window.location.protocol + '//' + 'localhost:7001/api/v1/';

  /**
   * Resource URL in the Rest class LOGIN
   */

  static readonly CONTACTS: string = '/contacts';

  static readonly CONTACT: string = '/contact';

  static readonly CONTACT_DETAIL: string = '/contact-detail';



}

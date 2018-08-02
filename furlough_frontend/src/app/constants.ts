export class Constants {
  public static readonly domain = '10.4.14.58';
  public static readonly port = '8080';
  public static readonly app = 'furlough';
  public static readonly baseUrl: string = 'http://' + Constants.domain + ':' + Constants.port + '/' + Constants.app + '/';
  public static reportsUrl(startDate, endDate) {
    return Constants.baseUrl + '/requests?from=' + startDate + '+&to=' + endDate ;
  }
  public static employeesUrl() {
    return Constants.baseUrl + 'ms_employees';
  }
  public static filesUrl() {
    return Constants.baseUrl + 'files';
  }
  public static saveNewUser() {
    return Constants.baseUrl + 'ms_employees';
  }
  public static loginUrl() {
    return Constants.baseUrl + 'login';
  }
  public static editUser(MSID) {
    return Constants.baseUrl + 'ms_employees/' + MSID;
  }
  public static deactivateUser(MSID) {
    return Constants.baseUrl + 'ms_employees/' + MSID;
  }
  public static fileUploadUrl() {
    return Constants.baseUrl + 'post';
  }
}

export interface Site {
  id: string;
  name: string;
  connector: {
    id: string;
    name?: string;
  };
  createdDate?: string;
  updatedDate?: string;
  siteToken: {
    id?: number;
    token: string;
    isValid?: boolean;
  };
}

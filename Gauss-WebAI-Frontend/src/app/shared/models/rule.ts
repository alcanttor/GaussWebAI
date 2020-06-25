export interface SubRule {
  emailTemplate: string;
  id: number;
  systemRule: {
    action: {
      id: number;
    };
    connector: {
      id: string;
    };
    systemEvent: {
      id: number;
      description?: string;
    };
  };
}

export interface Rule {
  id: number;
  name: string;
  siteId: number;
  description: string;
  enabled: boolean;
  rules: SubRule[];
}

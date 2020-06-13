// {
//     "id": 1,
//     "descripton": "Email Notification on role change",
//     "systemEvent": {
//       "id": 1,
//       "description": "ROLE CHANGE"
//     },
//     "action": {
//       "id": 1,
//       "name": "EMAIL"
//     },
//     "connector": {
//       "id": 1,
//       "name": "WORD PRESS"
//     }
//   }

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
  description: string;
  rules: SubRule[];
}

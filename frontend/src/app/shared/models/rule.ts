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

export interface Rule {
  id: number;
  name: string;
  description: string;
  action: {
    id: number;
    name: string;
  };
  connector: {
    id: number;
    name: string;
  };
  systemEvent: {
    id: number;
    description: string;
  };
}

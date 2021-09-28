import { Role } from "./role";
import { Ticket } from "./ticket";

export class Client {
  id! : string;
  name! : string;
  lastname! : string;
  password! : string;
  photo! : string;
  hierarchy! : string;
  role! : Role;
  department! : string;
  ticketList! : Array<Ticket>;
}

import {Component, OnInit} from '@angular/core';
import {Client} from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor() {
  }

  title = 'stomp-consumer';
  private client!: Client;
  messageList : string[] = [];

  ngOnInit(): void {
    this.client = new Client();
    this.client.webSocketFactory = () => {
      return new SockJS('http://localhost:8079/ws');
    };
    this.client.onConnect = (frame) => {
      this.client.subscribe('/topic/message',  (event) => {
        this.messageList.push(event.body);
      });
    };
    this.client.activate();
  }
}

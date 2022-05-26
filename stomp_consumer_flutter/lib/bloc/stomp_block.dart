import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

abstract class BlocEvent {}

class BlocNewEvent extends BlocEvent {
  final String message;
  BlocNewEvent({required this.message});
}

class StompBloc extends Bloc<BlocEvent, Stream<List<String>>> {
  late StompClient client;
  List<String> elementList = [];
  StompBloc() : super(const Stream.empty()) {

    client = StompClient(
        config: StompConfig(
            url: 'ws://localhost:8080/ws',
            onConnect: _onConnect,
            onWebSocketError: (dynamic error) => print(error.toString()),
    )
    );
    client.activate();
    on<BlocNewEvent>((event, emit) => emit(_onNewEvent(event)));
  }

  Stream<List<String>> _onNewEvent(BlocNewEvent event)async* {
    elementList.add(event.message);
    yield elementList;
  }

  void _onConnect(StompFrame frame) {
    client.subscribe(
      destination: '/topic/message',
      callback: (frame) => add(BlocNewEvent(message: frame.body!)),
    );
  }
}

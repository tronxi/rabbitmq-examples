import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:stomp_consumer_flutter/bloc/stomp_block.dart';

class StompPage extends StatelessWidget {
  const StompPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(providers: [
      BlocProvider<StompBloc>(
          create: (BuildContext context) => StompBloc()
      )
    ],
        child: const StompPageBody()
    );
  }
}

class StompPageBody extends StatelessWidget {
  const StompPageBody({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return BlocBuilder<StompBloc, Stream<List<String>>>(builder: (context, state) {
      return Scaffold(
          body: StreamBuilder<List<String>>(
              stream: state,
              initialData: const [],
              builder: (context, snapshot) {
                  return ListView.builder(
                    itemBuilder: (context, index) => Text(snapshot.data![index]),
                    itemCount: snapshot.data!.length,
                  );
              }
          )
      );
    }
    );
  }

}


# android-handler
Handler 
  Handler is Class that work on message queue model
  Handler put message on message Queue .
  Looper is responsible for enqueuing message and send to appropriate handler.
  Handler by default runs on main thread;

Thread Communication with Handler
  Handler can be created in thread by calling
    Looper.prepare . //Prepare looper class for specific thread.
    Looper.loop //Calling loop method to keep thread alive and enqueue message 
    
    Looper can be terminated by calling Looper.mylooper.quit()
    
 HandlerThread
    Handler Thread  provide a framework that creats a thread to take care of looper and handler.
    handlerthread can be stop by calling quit on handlerthread object
  

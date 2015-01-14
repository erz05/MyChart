package com.erz.mychart.util;

import android.os.AsyncTask;
import android.os.Build;

import com.erz.mychart.listeners.BackgroundTaskListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.RejectedExecutionException;

/*
 * This is a wrapper around ASyncTask that helps to formalise the business of reporting status and errors. 
 * You use this by creating a subclass and implementing the doExecute(...) method. 
 */
public abstract class BackgroundTask<ParamType, ResultType> extends AsyncTask<ParamType, BackgroundTask.BackgroundTaskStatus, ResultType> {
	
	private BackgroundTaskListener<ResultType> listener;
    private boolean finished;
	
	/**
	 * Subclasses should implement this method.
	 * @param params
	 * @return
	 */
	protected abstract ResultType doExecute(ParamType... params);

    public void run(ParamType... params){
        try{
            if (Build.VERSION.SDK_INT >= 11){
                this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
            }else{
                this.execute(params);
            }
        }catch(RejectedExecutionException e){
            listener.error(0, e, "Rejected Execution, expect bad things!");
        }
    }

    public BackgroundTask(BackgroundTaskListener<ResultType> listener) {
		this.listener = listener;
	}

	@Override
	protected final void onPreExecute() {
		listener.begin();
    }

	@Override
	protected final ResultType doInBackground(ParamType... params) {
	    return doExecute(params);
	}

	@Override
	protected final void onPostExecute(ResultType result) {
        finished = true;
		if (listener != null) listener.complete(result);
        listener = null;
	}	
	
	@Override
	protected final void onProgressUpdate(BackgroundTaskStatus... values) {

        if (listener == null) return;

        for (BackgroundTaskStatus value : values) {
            if (value.error != null)
                listener.error(value.code, value.error, value.message);
            else
                listener.progress(value.message);
        }

	}

	/**
	 * Subclasses should use this to report errors to their listener.
	 * @param code error code. if you don't want to supply this, use the error method that doesn't have this in the signature.
	 * @param throwable the throwable/exception that caused the error.
	 * @param message error message.
	 */
	protected void error(int code, Throwable throwable, String message) {
        finished = true;
		publishProgress(new BackgroundTaskStatus(message, throwable, code));
	}
	
	/**
	 * Subclasses should use this to report errors to their listener. This is the same as the other error method, but you don't have to supply 
	 * an error code to this one (-1, the default, will be used instead).
	 * @param throwable the throwable/exception that caused the error.
	 * @param message error message.
	 */
	protected void error(Throwable throwable, String message) {
        finished = true;
		publishProgress(new BackgroundTaskStatus(message, throwable));
	}
	
	/**
	 * Subclasses should use this to report progress to their listener. Currently this just takes a string. maybe it would be good for it to
	 * take other objects, perhaps an optional progress indicator etc.
	 * @param message progress message.
	 */
	protected void progress(String message) {
		publishProgress(new BackgroundTaskStatus(message));
	}
	
	/**
	 * A status report from a background task.  
	 */
	public static final class BackgroundTaskStatus {
		public String message;
		public Throwable error;
		public int code = -1;
		
		public BackgroundTaskStatus(String message) {
			this(message, null);
		}
		
		public BackgroundTaskStatus(String message, Throwable throwable) {
			this(message, throwable, -1);				
		}
		
		public BackgroundTaskStatus(String message, Throwable throwable, int code) {
			this.message = message;
			this.error = throwable;
			this.code = code;
		}
    }

    public boolean isFinished(){
        Status status = getStatus();
        if (finished){
            return true;
        }
        if (status == Status.FINISHED){
            return true;
        }
        if (status == Status.PENDING){
            return false;
        }
        if (status == Status.RUNNING){
            return false;
        }

        return false;
    }

    public String readerToString(Reader in){
        if(in != null){
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(in);
            String line;
            try {
                while ((line = reader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }

                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  null;
    }

    public JSONObject parseToObject(Reader in){
        JSONObject object = null;
        String str = readerToString(in);
        if(str != null){
            try {
                object = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public JSONArray parseToArray(Reader in){
        JSONArray array = null;
        String str = readerToString(in);
        if(str != null){
            try {
                array = new JSONArray(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}

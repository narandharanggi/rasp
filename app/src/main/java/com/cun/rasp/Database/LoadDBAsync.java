//public class LoadDbAsync extends AsyncTask <Void, Void, Boolean>{
//
//  Context context;
//  boolean loadedDb = false;
//  //private Activity mActivity;
//
//  //constructor
//   public LoadDbAsync(Context context) {
//          //mActivity = activity;
//       this.context = context;
//      }
//
//  private DataBaseHelper myDbHelper;
//  private ProgressDialog pDialog;
//
//  //Se ejecutará antes del código principal de nuestra tarea.
//  //Se suele utilizar para preparar la ejecución de la tarea, inicializar la interfaz, etc
//  @Override
//  protected void onPreExecute() {
//      // TODO Auto-generated method stub
//      super.onPreExecute();
//      Log.i("LocAndroid", "Entra en PreExecute");
//
//      pDialog = new ProgressDialog(context);
//      pDialog.setMessage("Cargando Base de datos...");
//      pDialog.setCancelable(false);//erabiltzaileak atzera botoia sakatuz ez kantzelatzeko
//      pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//barrarewn estiloa. Espiral bat izango da
//
//  }
//
//  //Se ejecutará cada vez que llamemos al método publishProgress() desde el método doInBackground().
//  //se usa para poder realizar cambios en la interface. En doInBackground no se pueden realizar cambios en la interface
//  @Override
//  protected void onProgressUpdate(Void... values) {
//      // TODO Auto-generated method stub
//      super.onProgressUpdate(values);
//
//      Log.i("LocAndroid", "Entra en onProgressUpdate");
//
//      pDialog.show();
//  }
//
//
//  @Override
//  protected Boolean doInBackground(Void... params) {
//      // TODO Auto-generated method stub
//      Log.i("LocAndroid", "Entra en doInBackground");
//
//      myDbHelper = new DataBaseHelper(context);
//      boolean dbExist = myDbHelper.checkDataBase();
//
//      if(dbExist){
//          loadedDb = true;
//
//
//      }else{
//
//          publishProgress(null);
//      }
//
//
//
//      try {
//          myDbHelper.createDataBase();
//
//      } catch (IOException e) {
//          // TODO Auto-generated catch block
//          throw new Error ("No se puede crear. Boton Crear. try. doInBAckground");
//      }
//      myDbHelper.close();
//
//
//      return null;
//  }
//
//
//  @Override
//  protected void onPostExecute(Boolean result) {
//      // TODO Auto-generated method stub
//      super.onPostExecute(result);
//      Log.i("LocAndroid", "Entra en onPostExecute");
//
//      if(!loadedDb){
//      pDialog.dismiss();
//      Log.i("LocAndroid", "Se termino de cargar la BD");
//      Toast.makeText(context, "Base de datos cargada", Toast.LENGTH_SHORT).show();
//      }else{
//          Log.i("LocAndroid", "La BD ya estaba cargada");
//
//      }
//          try {
//                  finalize();
//              } catch (Throwable e) {
//                  // TODO Auto-generated catch block
//                          e.printStackTrace();
//              }
//  }
//
//}
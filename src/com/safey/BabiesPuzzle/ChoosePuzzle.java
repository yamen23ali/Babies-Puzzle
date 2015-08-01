package com.safey.BabiesPuzzle;


import com.safey.BabiesPuzzle.R;

import myUtils.Controller;
import myUtils.MyAlertBox;
import myUtils.MyBitmapDecoder;
import myUtils.MyGridView;
import myUtils.MyImageResizer;
import myUtils.MyProgressBarMenu;
import myUtils.StatesInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ChoosePuzzle extends Activity {

	//======================//
	Bitmap[] images;
	Bitmap hiddenBitMap;
	StatesInfo[] states;
	int stage=0;
	int level;
	int statesNumber=25;
	int wantedWidth,wantedHeight;
	MyGridView gridview;
	float ratings[];	
	private SQLiteDatabase db;
	
	ScrollView container;
	//======================//
	
	//===============================================//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.choose_puzzle);
		//==================================================//
		
		//============== Call Dialog ADS ==========//
		Intent adsDiag=new Intent(this,MyADSDialogBox.class);
		startActivity(adsDiag);
		//============== Call Dialog ADS ==========//
		level= this.getIntent().getExtras().getInt("level");
		stage=Controller.stage; 
		//stage=25;
		//======= Fill Data In Arrays =====//
		fillRatings();
		fillData();
		//======= Fill Data In Arrays =====//
		
		//======= Build It =====//
		buildPuzzleMenu();
		//======= Build It =====//
 	}
	//===============================================//
	
	//============== Build Puzzle Menu ===============//
	void buildPuzzleMenu()
	{
		//=========== Get Screen Dimension =========//
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		//=========== Get Screen Dimension =========//
		
		//======== How Much The Image Take From Screen ======//
		int widthRatio=2,heightRatio=4;
		widthRatio=3;heightRatio=2;
		//======== How Much The Image Take From Screen ======//
		
		//======== Fill The images Array ========//
		wantedWidth=(width/widthRatio)-(width/10);
		wantedHeight=(height/heightRatio);
		fillImages();	
		//======== Fill The images Array ========//
		
		//============ Put Images In GridView ========//
		gridview=new MyGridView(getApplicationContext(),this,widthRatio,level,wantedWidth);
		gridview.generateGridView(images,states,R.layout.cell_layout,stage);
		
		container=(ScrollView) findViewById(R.id.gridContainer);
		container.addView(gridview);
		//============ Put Images In GridView ========//
	}
	//============== Build Puzzle Menu ===============//
	
	//============== Fill Puzzle Image Array ===============//
	void fillImages()
	{
		images =new Bitmap[statesNumber];
		MyBitmapDecoder mbd=new MyBitmapDecoder();
		MyImageResizer rI=new MyImageResizer();
		hiddenBitMap=mbd.decodeSampledBitmapFromResource(getResources(),R.drawable.hidden_puzzle,wantedWidth,wantedHeight);
		//============================//
		for(int i=0;i<statesNumber;i++)
		{
			//========= open just the user stages ======//
			if(i>=stage)
			{
				images[i]=rI.resizeIt(hiddenBitMap,wantedWidth,wantedHeight);
				break;
			}
			else
			{
				images[i]=rI.resizeIt(
						mbd.decodeSampledBitmapFromResource(getResources(),states[i].imageId,wantedWidth,wantedHeight),
						wantedWidth,
						wantedHeight
						);
			}
			
			//========= open just the user stages ======//
		}
	}
	//============== Fill Puzzle Image Array ===============//
	
	
	//======== Fill The Id's , Names , Places =========//
	private void fillData()
	{
		states=new StatesInfo[25];
		//=====A
		states[0]=new StatesInfo(R.drawable.baby1,"","",ratings[1],"");
		states[1]=new StatesInfo(R.drawable.baby2,"","",ratings[2],"");
		states[2]=new StatesInfo(R.drawable.baby3,"","",ratings[3],"");
		states[3]=new StatesInfo(R.drawable.baby4,"","",ratings[4],"");
		states[4]=new StatesInfo(R.drawable.baby5,"","",ratings[5],"");
		states[5]=new StatesInfo(R.drawable.baby6,"","",ratings[6],"");
		states[6]=new StatesInfo(R.drawable.baby7,"","",ratings[7],"");
		states[7]=new StatesInfo(R.drawable.baby8,"","",ratings[8],"");
		states[8]=new StatesInfo(R.drawable.baby9,"","",ratings[9],"");
		states[9]=new StatesInfo(R.drawable.baby10,"","",ratings[10],"");
		states[10]=new StatesInfo(R.drawable.baby11,"","",ratings[11],"");
		states[11]=new StatesInfo(R.drawable.baby12,"","",ratings[12],"");
		states[12]=new StatesInfo(R.drawable.baby13,"","",ratings[13],"");
		states[13]=new StatesInfo(R.drawable.baby14,"","",ratings[14],"");
		states[14]=new StatesInfo(R.drawable.baby15,"","",ratings[15],"");
		states[15]=new StatesInfo(R.drawable.baby16,"","",ratings[16],"");
		states[16]=new StatesInfo(R.drawable.baby17,"","",ratings[17],"");
		states[17]=new StatesInfo(R.drawable.baby18,"","",ratings[18],"");
		states[18]=new StatesInfo(R.drawable.baby19,"","",ratings[19],"");
		states[19]=new StatesInfo(R.drawable.baby20,"","",ratings[20],"");
		states[20]=new StatesInfo(R.drawable.baby22,"","",ratings[21],"");
		states[21]=new StatesInfo(R.drawable.baby22,"","",ratings[22],"");
		states[22]=new StatesInfo(R.drawable.baby23,"","",ratings[23],"");
		states[23]=new StatesInfo(R.drawable.baby24,"","",ratings[24],"");
		states[24]=new StatesInfo(R.drawable.baby25,"","",ratings[25],"");
		
	}
	//======== Fill The Id's , Names , Places =========//
		

	//============= On Back Click =============//
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		    //Handle the back button
		    if(keyCode == KeyEvent.KEYCODE_BACK) {
		    	this.finish();
		    }
		    return true;
	}
	//============= On Back Click =============//
	
	//=========== What To Do When This Activity Is Destroyed =======//
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		clear();
	}
	//=========== What To Do When This Activity Is Destroyed =======//
	
	//=========== Clear Data ==========//
	public void clear()
	{
    	gridview.removeAllViews();
    	container.removeAllViews();
		for(int i=0;i<images.length;i++)
    	{
			if(i>stage) break;
    		images[i].recycle();
    		images[i]=null;
    	}
    	hiddenBitMap.recycle();
    	hiddenBitMap=null;
    	System.gc();
	}
	//=========== Clear Data ==========//

	//================= To Fill The Ratings Matrix From DB ==========//
	public void fillRatings()
	{
		String sqlCommand="";
		String name=Controller.getUserName();
		//====== Initialize ======//
		ratings=new float[51];
		for(int i=0;i<51;i++)
		{
			ratings[i]=0.0f;
		}
		//====== Initialize ======//
		try{
			//====== Open And Create Table ======//
			db = openOrCreateDatabase("PuzzleDataBase",MODE_PRIVATE, null);
			sqlCommand="CREATE TABLE IF NOT EXISTS UserRating (Stage INT(3),Rating VARCHAR,User VARCHAR );";
			db.execSQL(sqlCommand);
			//====== Open And Create Table ======//
	
			//======= Get Values From DB =======//
			sqlCommand="SELECT * FROM UserRating WHERE User='"+name+"';";
			Cursor iterator = db.rawQuery(sqlCommand, null);
			int stageIndex = iterator.getColumnIndex("Stage");
			int ratingIndex = iterator.getColumnIndex("Rating");
			while (iterator.moveToNext()) 
			{
				ratings[iterator.getInt(stageIndex)]=Float.parseFloat(iterator.getString(ratingIndex));
			}
			//======= Get Values From DB =======//
			iterator.close();
			db.close();
		}
		catch(Exception e)
		{
			MyAlertBox mab=new MyAlertBox(this,"5: " + e.getMessage());
		}
	}
	//================= To Fill The Ratings Matrix From DB ==========//
}

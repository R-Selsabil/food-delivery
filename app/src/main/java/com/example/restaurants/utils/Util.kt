package com.example.restaurants

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun openPage(ctx: Context, param1:String,param2:String?){
    var intent:Intent
    val mobile = param1
    val web = param1
    try{
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
        ctx.startActivity(intent)
    }catch (e:ActivityNotFoundException){
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
        ctx.startActivity(intent)
    }
}

fun openMap(ctx: Context, latitude:String,longitude:String?){
    var intent:Intent
    intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
    ctx.startActivity(intent)
}

fun openPhone(ctx: Context, phone:String){
    var intent:Intent
    intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$phone"))
    ctx.startActivity(intent)
}
fun openMail(ctx: Context, mail:String){
    var intent:Intent
    intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"))
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
    try {
        //start email intent
        ctx.startActivity(Intent.createChooser(intent, "Choose Email Client..."))
    }
    catch (e: Exception){
        //if any thing goes wrong for example no email client application or any exception
        //get and show exception message
        Toast.makeText(ctx, e.message, Toast.LENGTH_LONG).show()
    }
    ctx.startActivity(intent)
}


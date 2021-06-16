package com.example.alqudsapp.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.alqudsapp.R
import com.example.alqudsapp.models.CityDetails
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_products.*

class PhotoDetailsActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore
    var categoryImageElement:String?=null
    private var progressDialog: ProgressDialog?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)


        db = Firebase.firestore
        showDialog()
            categoryImageElement=intent.getStringExtra("categoryImage")
            val catName=intent.getStringExtra("categoryName")
            Picasso.get().load(categoryImageElement).into(category_image)
            txt_category_name.text=catName
            getProductsAccordingToCategory("$catName")

          category_back_element.setOnClickListener {

            var i = Intent(this, PhotoActivity::class.java)
            startActivity(i)
        }

    }

    private fun getProductsAccordingToCategory(catName:String){
        val dataProduct = mutableListOf<CityDetails>()

        db.collection("image_details").whereEqualTo("name",catName)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.e("aya", "${document.id} -> ${document.get("name")}")
                        val id = document.id
                        val data = document.data
                        val name = data["name"] as String?
                        val description = data["description"] as String?

                        textView.text=description
                    }


                }
                hideDialog()
            }
    }

    private fun showDialog() {

        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage(" جاري تحميل صورة الحي ....")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hideDialog(){
        if(progressDialog!!.isShowing){

            progressDialog!!.dismiss()
        }
    }




}
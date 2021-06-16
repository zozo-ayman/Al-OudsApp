package com.example.alqudsapp.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alqudsapp.R
import com.example.alqudsapp.adapters.PhotoAdapter
import com.example.alqudsapp.models.City

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class PhotoActivity : AppCompatActivity() , PhotoAdapter.onCategoryItemClickListener {

    lateinit var db: FirebaseFirestore
    private var progressDialog: ProgressDialog?=null

//add2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        db = Firebase.firestore

        showDialog()
        getAllCategories()
    }

    override fun onItemClick(data: City, position: Int) {



        var i = Intent(this, PhotoDetailsActivity::class.java)
        i.putExtra("id", data.id)
        i.putExtra("categoryImage", data.imageCategoryElement)
        i.putExtra("categoryName", data.nameCategoryElement)
        startActivity(i)

    }

    private fun getAllCategories(){
        val categoryList= mutableListOf<City>()
        db.collection("image")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.e("aya", "${document.id} -> ${document.get("name")} -> ${document.get("src")}")
                        val id = document.id
                        val data = document.data
                        val categoryName = data["name"] as String?
                        val categoryImage = data["src"] as String?
                        categoryList.add(City(id, categoryImage, categoryName))
                    }
                    recycle_category?.layoutManager =
                        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    recycle_category.setHasFixedSize(true)
                    val categoriesAdapter = PhotoAdapter(this, categoryList, this)
                    recycle_category.adapter = categoriesAdapter
                }
                hideDialog()
            }
    }

    private fun showDialog() {

        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("جاري تحميل الاحياء ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hideDialog(){
        if(progressDialog!!.isShowing){

            progressDialog!!.dismiss()
        }
    }





}


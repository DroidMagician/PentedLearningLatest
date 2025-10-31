package com.pented.learningapp.widget.pdfviewer

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.pented.learningapp.R
import com.pented.learningapp.databinding.ListItemPdfPageBinding

/**
 * Created by Rajat on 11,July,2020
 */

internal class PdfViewAdapter(private val renderer: PdfRendererCore) :
    RecyclerView.Adapter<PdfViewAdapter.PdfPageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfPageViewHolder {
//        val v =
//            LayoutInflater.from(parent.context).inflate(R.layout.list_item_pdf_page, parent, false)
//        return PdfPageViewHolder(v)
        val binding = ListItemPdfPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PdfPageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return renderer.getPageCount()
    }

    override fun onBindViewHolder(holder: PdfPageViewHolder, position: Int) {
        holder.bind()
    }

    inner class PdfPageViewHolder(private val binding: ListItemPdfPageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(itemView) {
                binding.pageView.setImageBitmap(null)
                renderer.renderPage(adapterPosition) { bitmap: Bitmap?, pageNo: Int ->
                    if (pageNo != adapterPosition)
                        return@renderPage
                    bitmap?.let {
                        binding.pageView.layoutParams =  binding.pageView.layoutParams.apply {
                            height =
                                ( binding.pageView.width.toFloat() / ((bitmap.width.toFloat() / bitmap.height.toFloat()))).toInt()
                        }
                        binding.pageView.setImageBitmap(bitmap)
                        binding.pageView.animation = AlphaAnimation(0F, 1F).apply {
                            interpolator = LinearInterpolator()
                            duration = 300
                        }
                    }
                }
            }
        }
    }
}
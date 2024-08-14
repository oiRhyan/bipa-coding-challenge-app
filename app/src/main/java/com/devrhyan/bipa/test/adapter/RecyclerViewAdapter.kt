package com.devrhyan.bipa.test.adapter

import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devrhyan.bipa.test.R
import com.devrhyan.bipa.test.services.models.Nodes

class RecyclerViewAdapter(

) : Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    private var nodeList : List<Nodes>? = null

    //Node list starter
    fun setNodeList(list : List<Nodes>) {
        nodeList = list
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(view : View) : ViewHolder(view) {
        val nodeName = view.findViewById<TextView>(R.id.nodeName)
        val nodeKey = view.findViewById<TextView>(R.id.nodeKey)
        val nodeDataResult = view.findViewById<TextView>(R.id.nodeDataResult)
        val nodeChannel = view.findViewById<TextView>(R.id.nodeChannel)
        val nodeCapacity = view.findViewById<TextView>(R.id.nodeCapacity)
        val nodeLocation = view.findViewById<TextView>(R.id.nodeLocation)
    }

    //Inflating layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val LayoutInflater = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return RecyclerViewHolder(LayoutInflater)
    }

    override fun getItemCount(): Int {
        return nodeList?.size ?: 0
    }

    //API request notation for implementing Instant Converter
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = nodeList?.get(position)

        val unixFirstDate : Long? = item?.firstSeen?.toLong()
        val unixSecondDate : Long? = item?.updatedAt?.toLong()

        val sat = item?.capacity

        //Converting unix timestamp to date and hour
        val stateDataFirst  = unixFirstDate?.let { Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()) }
        val stateDataLast = unixSecondDate?.let { Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()) }

        val city = item?.city?.pt_br ?: item?.city?.en ?: "Não localizado"
        val country = item?.country?.pt_br ?: item?.country?.en ?: "Não localizado"

        holder.nodeName.text = item?.alias
        holder.nodeKey.text = item?.publicKey
        holder.nodeDataResult.text = "${stateDataFirst?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss"))} - ${stateDataLast?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss"))}"
        holder.nodeChannel.text = item?.channels.toString()
        holder.nodeCapacity.text = "${satToBTC(sat)} BTC"
        holder.nodeLocation.text = "$city - $country"

    }

    //Auxiliary function for converting sat to BTC
    fun satToBTC(sat : Long?) : Double {
        val converter = 1e-8
        if (sat != null) {
            return sat * converter
        }
        return 0.0
    }

}
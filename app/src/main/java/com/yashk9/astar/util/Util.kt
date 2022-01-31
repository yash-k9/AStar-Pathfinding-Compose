package com.yashk9.astar.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yashk9.astar.model.Spot
import kotlin.random.Random

const val TAG = "AStar"


fun setUpSpotMapList(size: Int,
                     onUpdateSpotPath: (MutableList<Spot>) -> Unit,
                     onUpdateArraySpots: (MutableList<MutableList<Spot>>) -> Unit
){
    val items: MutableList<MutableList<Spot>> = mutableListOf()
    for(i in 0 until size){
        items.add(mutableListOf())
        for(j in 0 until size){
            val random = Random.nextDouble(0.0, 1.0)
            var isObstacle = false
            if(random < 0.25){
                isObstacle = true
            }
            items[i].add(Spot(i, j, isObstacle = isObstacle))
        }
    }

    for(i in 0 until size){
        for(j in 0 until size){
            items[i][j].addNeighbors(items, size)
        }
    }

    items[0][0].isObstacle = false
    items[size-1][size-1].isObstacle = false

    onUpdateSpotPath(mutableListOf())
    onUpdateArraySpots(items)
}

@Composable
fun getScreenWidth(): Int {
    val metrics = LocalContext.current.resources.displayMetrics
    return metrics.widthPixels
}

@Composable
fun getScreenHeight(): Int {
    val metrics = LocalContext.current.resources.displayMetrics
    return metrics.heightPixels
}





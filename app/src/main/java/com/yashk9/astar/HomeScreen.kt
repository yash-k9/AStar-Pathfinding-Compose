package com.yashk9.astar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yashk9.astar.components.GraphMap
import com.yashk9.astar.model.Spot
import com.yashk9.astar.util.AStar
import com.yashk9.astar.util.getScreenWidth
import com.yashk9.astar.util.setUpSpotMapList
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalAnimationApi
@Composable
fun HomeScreen() {
    var isFindingPath by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val gridBoundary by remember { mutableStateOf(18)}
    var path by remember { mutableStateOf(mutableListOf<Spot>())}
    var arraySpot by remember { mutableStateOf(mutableListOf(mutableListOf<Spot>())) }

    val onUpdateArraySpots: (MutableList<MutableList<Spot>>) -> Unit = {
        arraySpot = mutableListOf()
        arraySpot = it
    }

    val onUpdateSpotPath: (MutableList<Spot>) -> Unit = {
        path = mutableListOf()
        path = it
    }

    val onComplete: () -> Unit = {
        isFindingPath = false
    }

    setUpSpotMapList(gridBoundary, onUpdateSpotPath, onUpdateArraySpots)

    Scaffold(backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = { Text("A* Pathfinding Algo") },
            )
        },
        bottomBar = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ){
                Button(modifier = Modifier.padding(10.dp), onClick = {
                   if(!isFindingPath){
                       setUpSpotMapList(gridBoundary, onUpdateSpotPath, onUpdateArraySpots)
                   }
                }){
                    Text("Generate Map")
                }
                Button(onClick = {
                    scope.launch {
                        if(!isFindingPath){
                            isFindingPath = true
                            AStar().aStarPath(gridBoundary, arraySpot, onUpdateSpotPath, onComplete)
                        }
                    }
                }) {
                    Text("Find Path")
                }
            }
        }) {

        GraphMap(
            canvasSize = getScreenWidth().toFloat(),
            gridBoundary = gridBoundary,
            spotArray = arraySpot,
            path = path,
            modifier = Modifier
                .fillMaxHeight(0.7f)
        )
    }
}

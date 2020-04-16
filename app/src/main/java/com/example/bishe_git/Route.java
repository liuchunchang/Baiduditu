package com.example.bishe_git;

import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class Route {
//    PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");
//    PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");
    public void Route(final BaiduMap mBaiduMap, int id, LatLng stNode_latLng, LatLng enNode_latLng) {
        PlanNode stNode= PlanNode.withLocation(stNode_latLng);
        PlanNode enNode= PlanNode.withLocation(enNode_latLng);
        RoutePlanSearch mSearch = RoutePlanSearch.newInstance();
        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                //创建WalkingRouteOverlay实例
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
                if (walkingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条数据为例)
                    //为WalkingRouteOverlay实例设置路径数据
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    //在地图上绘制WalkingRouteOverlay
                    overlay.addToMap();
                }else{
                    Log.d("liu", "空");
                }
            }
            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
                //创建MassTransitRouteOverlay实例
                MassTransitRouteOverlay overlay = new MassTransitRouteOverlay(mBaiduMap);
                if (massTransitRouteResult.getRouteLines() != null && massTransitRouteResult.getRouteLines().size() > 0){
                    //获取路线规划数据（以返回的第一条数据为例）
                    //为MassTransitRouteOverlay设置数据
                    overlay.setData(massTransitRouteResult.getRouteLines().get(0));
                    //在地图上绘制Overlay
                    overlay.addToMap();
                }else{
                    Log.d("liu", "空");
                }
            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                //创建DrivingRouteOverlay实例
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                if (drivingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条路线为例）
                    //为DrivingRouteOverlay实例设置数据
                    overlay.setData(drivingRouteResult.getRouteLines().get(0));
                    //在地图上绘制DrivingRouteOverlay
                    overlay.addToMap();
                }else{
                    Log.d("liu", "空");
                }
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
                //创建BikingRouteOverlay实例
                BikingRouteOverlay overlay = new BikingRouteOverlay(mBaiduMap);
                if (bikingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条路线为例）
                    //为BikingRouteOverlay实例设置数据
                    overlay.setData(bikingRouteResult.getRouteLines().get(0));
                    //在地图上绘制BikingRouteOverlay
                    overlay.addToMap();
                }else{

                }
            }
        };

        mSearch.setOnGetRoutePlanResultListener(listener);
        switch (id) {
            case 1:
                mBaiduMap.clear();
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                break;
            case 2:
                mBaiduMap.clear();
                mSearch.bikingSearch((new BikingRoutePlanOption())
                        .from(stNode)
                        .to(enNode)
                        // ridingType  0 普通骑行，1 电动车骑行
                        // 默认普通骑行
                        .ridingType(0));
                break;
            case 3:
                mBaiduMap.clear();
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                break;
            case 4:
                mBaiduMap.clear();
                mSearch.masstransitSearch((new MassTransitRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
                break;
            default:
                Log.d("liu", "出错");
        }
        mSearch.destroy();
    }
}

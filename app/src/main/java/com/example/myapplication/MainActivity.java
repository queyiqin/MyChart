package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.rrc.clb.utils.BarCharts;
import com.rrc.clb.utils.CombinedChartManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CombinedChart mCombinedChart1;
    private CombinedChart mCombinedChart2;
    private CombinedChart mCombinedChart3;
    private CombinedChart mCombinedChart4;
    private CombinedChart mCombinedChart5;
    private CombinedChart mCombinedChart6;
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCombinedChart2 = findViewById(R.id.chart2);
        mCombinedChart1 = findViewById(R.id.chart1);
        mCombinedChart3 = findViewById(R.id.chart3);
        mCombinedChart4 = findViewById(R.id.chart4);
        mCombinedChart5 = findViewById(R.id.chart5);
        mCombinedChart6 = findViewById(R.id.chart6);
        pieChart = findViewById(R.id.piechart);
        //x轴数据
        List<String> xData = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            xData.add(String.valueOf(i));
        }

        //2种直方图
        //y轴数据集合
        List<List<Float>> yBarDatas2 = new ArrayList<>();
        List<List<Float>> yLineDatas2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 20; j++) {

                float f = (float) (Math.random() * 100);
                yData.add(f);
            }
            yBarDatas2.add(yData);
            yLineDatas2.add(yData);
        }
        //3种直方图
        //y轴数据集合
        List<List<Float>> yBarDatas3 = new ArrayList<>();
        List<List<Float>> yLineDatas3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 20; j++) {

                float f = (float) (Math.random() * 100);
                yData.add(f);
            }
            yBarDatas3.add(yData);
            yLineDatas3.add(yData);
        }

        //4种直方图
        //y轴数据集合
        List<List<Float>> yBarDatas4 = new ArrayList<>();
        List<List<Float>> yLineDatas4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 20; j++) {

                float f = (float) (Math.random() * 100);
                yData.add(f);
            }
            yBarDatas4.add(yData);
            yLineDatas4.add(yData);
        }           //5种直方图
        //y轴数据集合
        List<List<Float>> yBarDatas5 = new ArrayList<>();
        List<List<Float>> yLineDatas5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 20; j++) {

                float f = (float) (Math.random() * 100);
                yData.add(f);
            }
            yBarDatas5.add(yData);
            yLineDatas5.add(yData);
        }

        //6种直方图
        //y轴数据集合
        List<List<Float>> yBarDatas6 = new ArrayList<>();
        List<List<Float>> yLineDatas6 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 20; j++) {

                float f = (float) (Math.random() * 100);
                yData.add(f);
            }
            yBarDatas6.add(yData);
            yLineDatas6.add(yData);
        }
        //颜色集合
        List<Integer> colors1 = new ArrayList<>();
        colors1.add(getResources().getColor(R.color.pie_0));

        List<Integer> colors2 = new ArrayList<>();
        colors2.add(getResources().getColor(R.color.pie_1_0));
        colors2.add(getResources().getColor(R.color.pie_1_1));


        List<Integer> colors3 = new ArrayList<>();
        colors3.add(getResources().getColor(R.color.pie_2_0));
        colors3.add(getResources().getColor(R.color.pie_2_1));
        colors3.add(getResources().getColor(R.color.pie_2_2));


        List<Integer> colors4 = new ArrayList<>();
        colors4.add(getResources().getColor(R.color.pie_3_0));
        colors4.add(getResources().getColor(R.color.pie_3_1));
        colors4.add(getResources().getColor(R.color.pie_3_2));
        colors4.add(getResources().getColor(R.color.pie_3_3));

        List<Integer> colors5 = new ArrayList<>();
        colors5.add(getResources().getColor(R.color.pie_4_0));
        colors5.add(getResources().getColor(R.color.pie_4_1));
        colors5.add(getResources().getColor(R.color.pie_4_2));
        colors5.add(getResources().getColor(R.color.pie_4_3));
        colors5.add(getResources().getColor(R.color.pie_4_4));
        List<Integer> colors6 = new ArrayList<>();
        colors6.add(getResources().getColor(R.color.pie_5_0));
        colors6.add(getResources().getColor(R.color.pie_5_1));
        colors6.add(getResources().getColor(R.color.pie_5_2));
        colors6.add(getResources().getColor(R.color.pie_5_3));
        colors6.add(getResources().getColor(R.color.pie_5_4));
        colors6.add(getResources().getColor(R.color.pie_5_5));


        //管理类 1条
        CombinedChartManager combineChartManager1 = new CombinedChartManager(mCombinedChart1);
        combineChartManager1.showCombinedChart(xData, yBarDatas4.get(0), yLineDatas4.get(0),
                colors1.get(0), colors1.get(0));
        //管理类 2条
        CombinedChartManager combineChartManager2 = new CombinedChartManager(mCombinedChart2);
        combineChartManager2.showCombinedChart(xData, yBarDatas2, yLineDatas2,
                colors2, colors2, 2.5f);

//        管理类 3条
        CombinedChartManager combineChartManager3 = new CombinedChartManager(mCombinedChart3);
        combineChartManager3.showCombinedChart(xData, yBarDatas3, yLineDatas3,
                colors3, colors3, 0);
        //管理类 4条
        CombinedChartManager combineChartManager4 = new CombinedChartManager(mCombinedChart4);
        combineChartManager4.showCombinedChart(xData, yBarDatas4, yLineDatas4,
                colors4, colors4, 0);

        //管理类 5条
        CombinedChartManager combineChartManager5 = new CombinedChartManager(mCombinedChart5);
        combineChartManager5.showCombinedChart(xData, yBarDatas5, yLineDatas5,
                colors5, colors5, 0);
        //// 管理类 6条
        CombinedChartManager combineChartManager6 = new CombinedChartManager(mCombinedChart6);
        combineChartManager6.showCombinedChart(xData, yBarDatas6, yLineDatas6,
                colors6, colors6, 4);

        ArrayList<PieEntry> pieEntryArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            float f = 0f;
            if (i == 0) {
                f = 250f;
            }
            if (i == 1) {
                f = 250f;
            }
            if (i == 2) {
                f = 500f;
            }
            Log.e("print", "onCreate: " + f);
            pieEntryArrayList.add(new PieEntry(f, "www" + i));
        }
        BarCharts barCharts = new BarCharts(pieChart);
        barCharts.showBarChart(pieEntryArrayList, colors3);
    }


}

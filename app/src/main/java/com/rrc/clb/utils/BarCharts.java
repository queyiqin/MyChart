package com.rrc.clb.utils;


import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class BarCharts {

    private PieChart pie_chart;

    public BarCharts(PieChart pie_chart) {
        this.pie_chart = pie_chart;
    }

    public void showBarChart(ArrayList<PieEntry> entries, List<Integer> colors) {
        pie_chart.setUsePercentValues(true);
        pie_chart.getDescription().setEnabled(false);
        pie_chart.setExtraOffsets(0.f, 10.f, 0.f, 10.f);
        pie_chart.setDragDecelerationFrictionCoef(0.95f);
        //中心是否绘制
        pie_chart.setDrawHoleEnabled(false);
//        pie_chart.setHoleColor(Color.WHITE); //中间圆颜色
        pie_chart.setTransparentCircleColor(Color.WHITE);
        pie_chart.setTransparentCircleAlpha(110);
        //圆半径
        pie_chart.setHoleRadius(0f);
        pie_chart.setTransparentCircleRadius(44f);
        pie_chart.setDrawCenterText(true);

        //旋转角度
        pie_chart.setRotationAngle(20);
        //旋转
        pie_chart.setRotationEnabled(true);
        //点击是否突出
        pie_chart.setHighlightPerTapEnabled(false);


        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        //不同块之间的间距
        dataSet.setSliceSpace(0f);
        //选中时候突出的间距
        dataSet.setSelectionShift(7f);
        dataSet.setColors(colors);
        dataSet.setValueLinePart1Length(0.7f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setHighlightEnabled(false);
        // //null  不显示百分比PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.setYValuePosition(null);
        //标签显示在外面，关闭显示在饼图里面PieDataSet.ValuePosition.OUTSIDE_SLICE)
        dataSet.setXValuePosition(null);
        //设置指示线条颜色,必须设置成这样，才能和饼图区域颜色一致
        dataSet.setValueLineColor(Color.parseColor("#181817"));
        dataSet.setValueLineVariableLength(true);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        //点击是否可见
        data.setHighlightEnabled(true);
        pie_chart.setData(data);
        pie_chart.highlightValues(null);
        pie_chart.invalidate();
        //设置饼图动画
        pie_chart.animateY(1200, Easing.EasingOption.EaseInOutQuad);
        Legend l = pie_chart.getLegend();
        //
        l.setEnabled(false);


    }

}

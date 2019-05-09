package com.rrc.clb.utils;

import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;


public class CombinedChartManager {

    private CombinedChart mCombinedChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    public CombinedChartManager(CombinedChart combinedChart) {
        this.mCombinedChart = combinedChart;
        leftAxis = mCombinedChart.getAxisLeft();
        rightAxis = mCombinedChart.getAxisRight();
        xAxis = mCombinedChart.getXAxis();
    }

    /**
     * 初始化Chart
     */
    private void initChart() {


        //显示描述内容
        mCombinedChart.getDescription().setEnabled(false);

        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.LINE
        });

        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);
        //缩放
        mCombinedChart.setScaleEnabled(false);
        //不显示显示边界
        mCombinedChart.setDrawBorders(false);
        //图例说明
        Legend legend = mCombinedChart.getLegend();
        //不显示标签
        legend.setEnabled(false);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);
//        legend.setWordWrapEnabled(true);
//
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        legend.setDrawInside(false);
        //Y轴设置
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        mCombinedChart.setExtraTopOffset(30);
        mCombinedChart.setExtraBottomOffset(10);
        mCombinedChart.getAxisRight().setEnabled(false);
        mCombinedChart.animateX(2000); // 立即执行的动画,x轴
    }

    /**
     * 设置X轴坐标值
     *
     * @param xAxisValues x轴坐标集合
     */
    public void setXAxis(final List<String> xAxisValues) {

        //设置X轴在底部
        XAxis xAxis = mCombinedChart.getXAxis();
        //设置x轴标签 即x轴上显示的数值
        xAxis.setDrawLabels(true);
        //是否设置x轴上每个点对应的线 即 竖向的网格
        xAxis.setDrawGridLines(false);


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(-0.3f);
        xAxis.setAxisMaximum((float) (xAxisValues.size() + 0.3));
//        xAxis.setLabelCount(5, false);
//        xAxis.setLabelCount(xAxisValues.size()+1, false);

        xAxis.setLabelCount(xAxisValues.size() + 2);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if (v < 0 || v > (xAxisValues.size() - 1)) {//使得两侧柱子完全显示
                    return "";
                }
                return xAxisValues.get((int) v);
            }
        });

        mCombinedChart.invalidate();
    }

    /**
     * 得到折线图(一条)
     *
     * @param lineChartY 折线Y轴值
     * @param lineColor  折线颜色
     * @return
     */
    private LineData getLineData(List<Float> lineChartY, int lineColor) {
        LineData lineData = new LineData();

        ArrayList<Entry> yValue = new ArrayList<>();
        for (int i = 0; i < lineChartY.size(); i++) {
            yValue.add(new Entry(i, lineChartY.get(i)));
        }
        //标签名字为空
        LineDataSet dataSet = new LineDataSet(yValue, "");
        dataSet.setColor(lineColor);
        dataSet.setCircleColor(lineColor);
        dataSet.setValueTextColor(lineColor);

        dataSet.setCircleSize(1);
        //显示值
        dataSet.setDrawValues(false);

        //在点上画圆 默认true
        dataSet.setDrawCircles(false);
        dataSet.setValueTextSize(10f);
//        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineData.addDataSet(dataSet);


        return lineData;
    }

    /**
     * 得到折线图(多条)
     *
     * @param lineChartYs 折线Y轴值
     * @param lineColors  折线颜色
     * @return
     */
    private LineData getLineData(List<List<Float>> lineChartYs, List<Integer> lineColors) {

//        String[] xData = new String[lineChartYs.get(0).size()];
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < lineChartYs.get(0).size(); i++) {
            arrayList.add(lineChartYs.get(0).get(i) + "");
        }
//
        LineData lineData = new LineData();
        for (int i = 0; i < lineChartYs.size(); i++) {
            ArrayList<Entry> yValues = new ArrayList<>();
            for (int j = 0; j < lineChartYs.get(i).size(); j++) {

                //多条折线时，手动调节x轴坐标点
                if (lineChartYs.size() == 2) {
                    if (i == 0) {
                        yValues.add(new Entry((float) (j + 0.3), lineChartYs.get(i).get(j)));
                    }
                    if (i == 1) {
                        yValues.add(new Entry((float) (j + 0.7), lineChartYs.get(i).get(j)));
                    }
                }
                if (lineChartYs.size() == 3) {
                    if (i == 0) {
                        yValues.add(new Entry((float) (j + 0.24), lineChartYs.get(i).get(j)));
                    }
                    if (i == 1) {
                        yValues.add(new Entry((float) (j + 0.54), lineChartYs.get(i).get(j)));
                    }
                    if (i == 2) {
                        yValues.add(new Entry((float) (j + 0.8), lineChartYs.get(i).get(j)));
                    }
                }
                if (lineChartYs.size() == 4) {
                    if (i == 0) {
                        yValues.add(new Entry((float) (j + 0.2), lineChartYs.get(i).get(j)));
                    }
                    if (i == 1) {
                        yValues.add(new Entry((float) (j + 0.4), lineChartYs.get(i).get(j)));
                    }
                    if (i == 2) {
                        yValues.add(new Entry((float) (j + 0.6), lineChartYs.get(i).get(j)));
                    }
                    if (i == 3) {
                        yValues.add(new Entry((float) (j + 0.8), lineChartYs.get(i).get(j)));
                    }
                }
                if (lineChartYs.size() == 5) {
                    if (i == 0) {
                        yValues.add(new Entry((float) (j + 0.2), lineChartYs.get(i).get(j)));
                    }
                    if (i == 1) {
                        yValues.add(new Entry((float) (j + 0.35), lineChartYs.get(i).get(j)));
                    }
                    if (i == 2) {
                        yValues.add(new Entry((float) (j + 0.5), lineChartYs.get(i).get(j)));
                    }
                    if (i == 3) {
                        yValues.add(new Entry((float) (j + 0.65), lineChartYs.get(i).get(j)));
                    }
                    if (i == 4) {
                        yValues.add(new Entry((float) (j + 0.8), lineChartYs.get(i).get(j)));
                    }
                }

                if (lineChartYs.size() == 6) {
                    if (i == 0) {
                        yValues.add(new Entry((float) (j + 0.15), lineChartYs.get(i).get(j)));
                    }
                    if (i == 1) {
                        yValues.add(new Entry((float) (j + 0.3), lineChartYs.get(i).get(j)));
                    }
                    if (i == 2) {
                        yValues.add(new Entry((float) (j + 0.45), lineChartYs.get(i).get(j)));
                    }
                    if (i == 3) {
                        yValues.add(new Entry((float) (j + 0.58), lineChartYs.get(i).get(j)));
                    }
                    if (i == 4) {
                        yValues.add(new Entry((float) (j + 0.7), lineChartYs.get(i).get(j)));
                    }
                    if (i == 5) {
                        yValues.add(new Entry((float) (j + 0.85), lineChartYs.get(i).get(j)));
                    }
                }

            }
            //标签为空
            LineDataSet dataSet = new LineDataSet(yValues, "");

            dataSet.setColor(lineColors.get(i));
            dataSet.setCircleColor(lineColors.get(i));
            dataSet.setValueTextColor(lineColors.get(i));
            dataSet.setCircleSize(1);
            //在点上显示数值 默认true
            dataSet.setDrawValues(false);
            //在点上画圆 默认true
            dataSet.setDrawCircles(false);
            dataSet.setValueTextSize(10f);
            //使折线变透明不可见
//            dataSet.setColor(Color.parseColor("#ffffff"),0);
//            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);


            lineData.addDataSet(dataSet);

            YAxis leftAxis = mCombinedChart.getAxisLeft();
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//            leftAxis.setStartAtZero(false);//设置Y轴的数据不是从0开始
            leftAxis.setDrawTopYLabelEntry(true);

        }
        return lineData;
    }

    /**
     * 得到柱状图
     *
     * @param barChartY Y轴值
     * @param barColor  柱状图颜色
     * @return
     */

    private BarData getBarData(List<Float> barChartY, int barColor) {
        BarData barData = new BarData();
        ArrayList<BarEntry> yValues = new ArrayList<>();
        for (int i = 0; i < barChartY.size(); i++) {
            yValues.add(new BarEntry(i, barChartY.get(i)));
        }
        //标签名字为空
        BarDataSet barDataSet = new BarDataSet(yValues, "");
        barDataSet.setColor(barColor);
        barDataSet.setValueTextSize(10f);
        barDataSet.setValueTextColor(barColor);
        barDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        barData.addDataSet(barDataSet);

        //是否显示值
        barDataSet.setDrawValues(false);


        //以下是为了解决 柱状图 左右两边只显示了一半的问题 根据实际情况 而定
        xAxis.setAxisMinimum(-0.3f);

        float groupSpace = 0.2f;
        float barSpace = 0f;
        float barWidth = 0.2f;

        barData.setBarWidth(barWidth);

        return barData;
    }

    /**
     * 得到柱状图(多条)
     *
     * @param barChartYs Y轴值
     * @param barColors  柱状图颜色
     * @return
     */

    private BarData getBarData(List<List<Float>> barChartYs, List<Integer> barColors) {
        List<IBarDataSet> lists = new ArrayList<>();
        for (int i = 0; i < barChartYs.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();

            for (int j = 0; j < barChartYs.get(i).size(); j++) {
                entries.add(new BarEntry(j, barChartYs.get(i).get(j)));
            }
//            barNames.get(i) 名字
            BarDataSet barDataSet = new BarDataSet(entries, "");

            barDataSet.setColor(barColors.get(i));
            barDataSet.setValueTextColor(barColors.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
            //是否显示值
            barDataSet.setDrawValues(false);

//            barDataSet.setColor(Color.parseColor("#ffffff"),0);
            lists.add(barDataSet);
        }
        BarData barData = new BarData(lists);
//需要显示柱状图的类别 数量
        int amount = barChartYs.size();
        //柱状图组之间的间距
        float groupSpace = 0.21f;
        float barSpace = (float) ((1 - 0.12) / amount / 10);
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9);

        // (0.2 + 0.02) * 4 + 0.12 = 1.00 即100% 按照百分百布局
        //柱状图宽度
        //显示值

        barData.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        barData.groupBars(0, groupSpace, 0);
        return barData;
    }

    /**
     * 显示混合图(柱状图+折线图)
     *
     * @param xAxisValues X轴坐标
     * @param barChartY   柱状图Y轴值
     * @param lineChartY  折线图Y轴值
     * @param barColor    柱状图颜色
     * @param lineColor   折线图颜色
     */

    public void showCombinedChart(
            final List<String> xAxisValues, List<Float> barChartY, List<Float> lineChartY
            , int barColor, int lineColor) {
        initChart();

        //        setXAxis(xAxisValues);
        XAxis xAxis = mCombinedChart.getXAxis();
        //设置x轴标签 即x轴上显示的数值
        xAxis.setDrawLabels(true);
        //是否设置x轴上每个点对应的线 即 竖向的网格
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(-0.3f);
        xAxis.setAxisMaximum((float) (xAxisValues.size() - 0.3));


        //  xAxis.setLabelCount(xAxisValues.size());
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                //使得两侧柱子完全显示
                if (v < 0 || v > (xAxisValues.size())) {
                    return "";
                }
                Log.e("print", "getFormattedValue: " + xAxisValues.get((int) v));
                return xAxisValues.get((int) v) + "---" + "1月13号";
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        CombinedData combinedData = new CombinedData();

        combinedData.setData(getBarData(barChartY, barColor));
        combinedData.setData(getLineData(lineChartY, lineColor));

        mCombinedChart.setData(combinedData);

        mCombinedChart.invalidate();
        Matrix mMatrix = new Matrix();
        mMatrix.postScale(3f, 1);
        mCombinedChart.getViewPortHandler().refresh(mMatrix, mCombinedChart, false);
        mCombinedChart.animateY(1000);
    }

    /**
     * 显示混合图(柱状图+折线图)
     *
     * @param xAxisValues X轴坐标
     * @param barChartYs  柱状图Y轴值
     * @param lineChartYs 折线图Y轴值
     * @param barColors   柱状图颜色
     * @param lineColors  折线图颜色
     */

    public void showCombinedChart(
            List<String> xAxisValues, List<List<Float>> barChartYs, List<List<Float>> lineChartYs,
            List<Integer> barColors, List<Integer> lineColors, float multiple) {
        initChart();
        setXAxis(xAxisValues);


        CombinedData combinedData = new CombinedData();

        combinedData.setData(getBarData(barChartYs, barColors));
        combinedData.setData(getLineData(lineChartYs, lineColors));

        //设置数据
        mCombinedChart.setData(combinedData);


        mCombinedChart.invalidate();
        Matrix mMatrix = new Matrix();
        // 为了使 柱状图成为可滑动的,将水平方向 放大 3倍
        if (multiple > 0) {
            mMatrix.postScale(multiple, 1);
        } else {
            mMatrix.postScale(3, 1);
        }

        mCombinedChart.getViewPortHandler().refresh(mMatrix, mCombinedChart, false);
        mCombinedChart.animateY(1000);


    }
}

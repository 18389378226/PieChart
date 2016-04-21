# CustomView<br>
## Introduction<br>
&nbsp;&nbsp;&nbsp;&nbsp;可定义环形块颜色与名称，显示数据百分比的环形图。用户可自定义动画效果，百分比小数位，画笔颜色，字体大小等。<br>
## Usage<br>
* 新建Pieview<br>
```java
PieView mPieView = new PieView(this);
```
* 设置初始角度<br>
```java
mPieView.setStartAngle(0);
```
* 设置数据<br>
```java
mPieView.setPieData(mPieDatas);
//初始化mPieView
private void initData(){
        for (int i=0; i<9; i++){
            PieData pieData = new PieData();
            pieData.setName("区域名);
            pieData.setValue(i+1);
            pieData.setColor(mColors[i]);
            Log.i("colorOld",Integer.toHexString(mColors[i]));
            mPieDatas.add(pieData);
        }
    }
```

## More<br>
* PieView更多设置请查看源码<br>

## Download<br>
* [jar](https://github.com/Idtk/CustomView/blob/master/jar/)<br>

## Demo<br>
<img src="https://github.com/Idtk/CustomView/blob/master/gif/CustomView.gif" alt="GitHub" title="GitHub,Social Coding"/><br>

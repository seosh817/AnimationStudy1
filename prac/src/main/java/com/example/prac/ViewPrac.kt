package com.example.prac

import android.content.Context
import android.graphics.Canvas
import android.view.View

class ViewPrac(context: Context) : View(context) {



    // View.measure()를 부르면 -> View.onMeasure() -> View.setMeasuredDimension()

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) { //필요할때만 불림( 예를들면 화면 꺼졌다 켜지면 안불림)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //measureSpec -> AT_MOST, EXACTLY, UNSPECIFIED
        // EXACTLY -> fill_parent or match_parent 혹은 dp로 크기를 정했을 경우(정확한 크기를 지정하였을 때) 크기를 미리 지정해서 width와 height을 넘겨주므로 MeasureSpec.getSize()로 그냥 사용하면 된다. (외부에서 크기가 정해져있음)
        // AT_MOST -> wrap_content일 경우 (별다른 크기를 지정해주지 않았을 경우 AT_MOST가 리턴됨 -> 그래서 크기를 내가 지정해주어야 한다)
        // UNSPECIFIED -> mode가 셋팅되지 않은 크기가 넘어올 때
        widthMeasureSpec
        MeasureSpec.getMode(widthMeasureSpec) // 이거 하면 셋중하나가 나옴 -> AT_MOST(match_parent), EXACTLY
        MeasureSpec.getSize(widthMeasureSpec) // 만약 30dp면 30dp에 해당하는 픽셀이 날라올것 기본 적인건 super.onMeasure에서 해줌
        setMeasuredDimension(measuredWidth, measuredWidth)

        //만약 AT_MOST나 EXACTLY라면 view 자체에서 크기를 아니까 MeasureSpec.getSize를 통해 사이즈를 지정하고
        //UNSPECIFIED라면 suggestedMinimumWidth사이즈로 지정
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)

        MeasureSpec.getSize(widthMeasureSpec) // xml에 있는 width 값이 날라옴

        invalidate() // invalidate()는 onDraw()가 필요 할 때 넣어주면 onDraw()가 불림
        requestLayout() //
        width // view의 최종적인 width
        //int 값만큼 width를 결정해줌
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        //measure로 결정이 되고 어딘가에 레이아웃이 잡히면 left top right bottom 으로 설정이 되었다
        //subView 들의 좌표를 설정해 주기 위해 ( 만약 부모 뷰의 사이즈가 줄어들면 그안에 subView도 줄어들어야하기 때문에)
        //subView들이 어디에 위치해야 하는지

        //score에따라서 circle의 위치를 계산해야한다면
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //화면에 그려지는 작업이 있을 때 불려짐, 뷰는 기본으로 onDraw()를 통해 그려짐
        //비트맵은 이미지 객체, 화면은 화면에 있는 캔버스

        //캔버스 drawRect, drawOval, drawPath 등등...
        //캔버스는 자바 버츄어머신 위에서 돌리는거고, openGL c++레이어에서 바로 그려지는 것(성능이 좋음)

        //뷰 안에 여러개의 서브뷰가 있다면 하이러키 면에서 맨위에 ondraw()가 불리면 그아래에 있는 서브뷰의 onDraw()가 불리면서 전체화면이 불림
        //drawCircle로 뷰를 그리던가 혹은 LinearLayout에서 서브뷰를 만들어주던가(서브뷰의 onMeasure가 필요 없음(불리는데 필요가 없음))

        //typedArray 가져오고 recycle()해주어야함
    }
}
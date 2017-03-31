package krishikalyan.aupadhyay.myapp.mycards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import krishikalyan.aupadhyay.myapp.R;

/**
 * Created by aupadhyay on 4/1/17.
 */

public class HashtagView extends FrameLayout {

    private static final int LINE_COLOUR = 0x59ffffff;

    private Paint mLinesPaint;
    private float[] mLinePoints;

    public HashtagView(Context context) {
        this(context, null, 0);
    }

    public HashtagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HashtagView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinesPaint.setColor(LINE_COLOUR);
        mLinesPaint.setStyle(Paint.Style.STROKE);
        mLinesPaint.setStrokeWidth(context.getResources().getDimensionPixelSize(
                R.dimen.hashtag_line_height));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // calculate points for two horizontal lines spaced at 1/3 & 2/3 of the height, occupying
        // 2/3 of the width (centered).
        final int thirdHeight = getMeasuredHeight() / 3;
        final int sixthWidth = getMeasuredWidth() / 6;
        mLinePoints = new float[]{
                // line 1
                sixthWidth, thirdHeight, 5 * sixthWidth, thirdHeight,
                // line 2
                sixthWidth, 2 * thirdHeight, 5 * sixthWidth, 2 * thirdHeight};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLines(mLinePoints, mLinesPaint);
    }
}


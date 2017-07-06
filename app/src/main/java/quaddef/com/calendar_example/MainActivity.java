package quaddef.com.calendar_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DayViewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarPickerView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarPickerView) findViewById(R.id.calendar_view);

        Date endDate = new Date(new Date().getTime() + 86400000);
        Date startDate = new Date(endDate.getTime() - 31536000000L);

        calendarView.init(startDate, endDate).inMode(CalendarPickerView.SelectionMode.RANGE);
        calendarView.setCustomDayView(new DayViewAdapter() {
            @Override
            public void makeCellView(CalendarCellView parent) {
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_calendar_cell, parent);
                parent.setDayOfMonthTextView((TextView) parent.getChildAt(0).findViewById(R.id.calendar_cell_text_view));
            }
        });
        calendarView.setDecorators(new ArrayList<CalendarCellDecorator>() {{
            add(new CellDecorator(calendarView));
        }});
    }
}

class CellDecorator implements CalendarCellDecorator{
    private CalendarPickerView calendar;

    CellDecorator(CalendarPickerView calendar){
        this.calendar = calendar;
    }

    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        List<Date> selectedDates = calendar.getSelectedDates();
        ImageView image = cellView.getChildAt(0).findViewById(R.id.calendar_cell_background_image);

        if (selectedDates.contains(date)){
            image.setImageResource(R.drawable.calendar_cell_range_middle);
        }else{
            image.setImageResource(0);
        }
    }
}

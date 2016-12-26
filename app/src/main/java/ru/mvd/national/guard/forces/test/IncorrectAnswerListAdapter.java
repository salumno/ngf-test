package ru.mvd.national.guard.forces.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IncorrectAnswerListAdapter extends BaseAdapter {

    private ArrayList<DataFieldResult> listData;
    private LayoutInflater layoutInflater;

    public IncorrectAnswerListAdapter(Context aContext, ArrayList<DataFieldResult> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
       return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.incorrect_answer_list_item, null);
            holder = new ViewHolder();
            holder.questionView = (TextView) view.findViewById(R.id.questionIncorrect);
            holder.incorrectAnswer  = (TextView) view.findViewById(R.id.userAnswerIncorrect);
            holder.correctAnswer  = (TextView) view.findViewById(R.id.correctAnswer);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.questionView.setText(listData.get(i).getQuestion());
        holder.incorrectAnswer.setText(listData.get(i).getUserIncorrectAnswer());
        holder.correctAnswer.setText(listData.get(i).getRightAns());
        return view;
    }

    static class ViewHolder {
        TextView questionView;
        TextView incorrectAnswer;
        TextView correctAnswer;

    }
}

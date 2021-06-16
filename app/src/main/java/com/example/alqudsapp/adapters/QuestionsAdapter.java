package com.example.alqudsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alqudsapp.R;
import com.example.alqudsapp.models.Question;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private Context context;
    private List<Question> questions;

    public QuestionsAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_list_item, parent, false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {

        Question question = questions.get(position);

        holder.questionTv.setText(question.getText());
        holder.answerTv.setText(question.getAnswer());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {

        TextView

                questionTv ,answerTv;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTv = itemView.findViewById(R.id.questionTv);
            answerTv = itemView.findViewById(R.id.answerTv);

        }

    }

}

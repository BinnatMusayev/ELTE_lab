package practice.com.eltelinks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import practice.com.eltelinks.MainActivity;
import practice.com.eltelinks.R;
import practice.com.eltelinks.model.Teacher;

public class TeacherRecyclerViewAdapter extends RecyclerView.Adapter<TeacherRecyclerViewAdapter.TeacherViewHolder> {

    private List<Teacher> teachers;

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.teacher_item, parent, false);
        return new TeacherViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder teacherViewHolder, int i) {
        final Teacher teacher = teachers.get(i);

        teacherViewHolder.bind(teacher);

        final int pos = i;

        teacherViewHolder.itemView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean expanded = teacher.isExpanded();
                    teacher.setExpanded(!expanded);
                    notifyItemChanged(pos);
                }
            });
    }

    public void setTeachers(List<Teacher> teachers){
        this.teachers = teachers;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return teachers == null ? 0 : teachers.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder{

        private TextView name, email, faculty, website;
        private TextView genre;
        private TextView year;
        private View subItem;

        public TeacherViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.teacher_name);
            email = itemView.findViewById(R.id.teacher_email);
            faculty = itemView.findViewById(R.id.teacher_faculty);
            website = itemView.findViewById(R.id.teacher_website);
            subItem = itemView.findViewById(R.id.sub_item);

            final View v = itemView;
            website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)v.getContext()).openBrowser(website.getText().toString());
                }
            });

        }

        private void bind(Teacher teacher) {
            boolean expanded = teacher.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            name.setText(teacher.getName());
            email.setText(teacher.getEmail());
            faculty.setText(teacher.getFaculty());
            website.setText(teacher.getWebsite());
        }

    }
}

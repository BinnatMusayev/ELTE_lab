package practice.com.eltelinks;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.List;

import practice.com.eltelinks.adapters.WebsitesAdapter;
import practice.com.eltelinks.helper.MyDragShadowBuilder;
import practice.com.eltelinks.model.Website;
import practice.com.eltelinks.view_model.WebsiteViewModel;

public class Websites_Fragment extends Fragment {

    private GridView gridView;
    private WebsitesAdapter websitesAdapter;
    //Floating Action Button
    private FloatingActionButton fab_websites;

    //View Model
    private WebsiteViewModel websiteViewModel;

    private Website selectedWebsite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_websites, container, false);

        websiteViewModel = ViewModelProviders.of(this).get(WebsiteViewModel.class);

        gridView = view.findViewById(R.id.websites_grid);
        websitesAdapter = new WebsitesAdapter(getActivity(), websiteViewModel.getAllWebsites().getValue());
        gridView.setAdapter(websitesAdapter);

        websiteViewModel.getAllWebsites().observe(getActivity(), new Observer<List<Website>>() {
            @Override
            public void onChanged(@Nullable List<Website> websites) {
                websitesAdapter.setWebsites(websites);
            }
        });


        //floating action button
        fab_websites = view.findViewById(R.id.fab_websites);

        fab_websites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddWebsite.class);
                startActivity(intent);
            }
        });

        //grid item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
                // opening fragment with URL data
                ((MainActivity)getActivity()).openBrowser(websiteViewModel.getAllWebsites().getValue().get(position).getUrl());

            }
        });

        //drag to delete

        gridView.setOnDragListener(new myDragEventListener());

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //show trash icon
                getActivity().findViewById(R.id.trash).setVisibility(View.VISIBLE);
                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                ClipData.Item item = new ClipData.Item(String.valueOf(view.getTag()));

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                ClipData dragData = new ClipData(
                        String.valueOf(view.getTag()),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                // Instantiates the drag shadow builder.
                ImageView imageView = websitesAdapter.getWebsiteLogo(position);
                selectedWebsite = websiteViewModel.getAllWebsites().getValue().get(position);
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(getActivity(), imageView);

                // Starts the drag

                view.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );


                return true;

            }
        });

        return view;
    }

    protected class myDragEventListener implements View.OnDragListener {

        private Point touchPosition;
        // This is the method that the system calls when it dispatches a drag event to the
        // listener.
        public boolean onDrag(View v, DragEvent event) {

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            ImageButton ib = getActivity().findViewById(R.id.trash);

            //Animation
            ObjectAnimator trashAnimation;
            AnimatorSet animatorSet = new AnimatorSet();
            long animationDuration =500; //milliseconds

            // Handles each of the expected events
            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    trashAnimation = ObjectAnimator.ofFloat(ib, "y", ib.getY()-500);
                    trashAnimation.setDuration(animationDuration);
                    animatorSet.playTogether(trashAnimation);
                    animatorSet.start();


                    // Determines if this View can accept the dragged data
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        // Invalidate the view to force a redraw in the new tint
                        v.invalidate();

                        // returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:


                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    touchPosition = getTouchPositionFromDragEvent(v, event);



//                    TextView t = getActivity().findViewById(R.id.websites_title);
//                    t.setText("X: " +touchPosition.x + ", Y: "+ touchPosition.y);
                    if (touchPosition.x>ib.getX()-ib.getWidth() && touchPosition.x <ib.getX()+ib.getWidth()*2
                            && touchPosition.y >ib.getY()+ib.getHeight() ){
                        ib.setImageResource(R.drawable.ic_delete_forever_black_24dp);
                    }else{
                        ib.setImageResource(R.drawable.ic_delete_black_24dp);
                    }

                    // Ignore the event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DROP:

                    touchPosition = getTouchPositionFromDragEvent(v, event);

                    if (touchPosition.x>ib.getX()-ib.getWidth() && touchPosition.x <ib.getX()+ib.getWidth()*2
                            && touchPosition.y >ib.getY()+ib.getHeight() ){
                        websiteViewModel.deleteWebsite(selectedWebsite);
                        Toast.makeText(getActivity(), "Deleted: " + selectedWebsite.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
//
//                    TextView t2 = getActivity().findViewById(R.id.websites_title);
//                    t2.setText(t2.getText().toString() + "||| " +ib.getX()+", "+ib.getY());


                    trashAnimation = ObjectAnimator.ofFloat(ib, "y", ib.getY()+500);
                    trashAnimation.setDuration(animationDuration);
                    animatorSet.playTogether(trashAnimation);
                    animatorSet.start();

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
//                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }

        public Point getTouchPositionFromDragEvent(View item, DragEvent event) {
            Rect rItem = new Rect();
            item.getGlobalVisibleRect(rItem);
            return new Point(rItem.left + Math.round(event.getX()), rItem.top + Math.round(event.getY()));
        }

        public boolean isTouchInsideOfView(View view, Point touchPosition) {
            Rect rScroll = new Rect();
            view.getGlobalVisibleRect(rScroll);
            return isTouchInsideOfRect(touchPosition, rScroll);
        }

        public boolean isTouchInsideOfRect(Point touchPosition, Rect rScroll) {
            return touchPosition.x > rScroll.left && touchPosition.x < rScroll.right //within x axis / width
                    && touchPosition.y > rScroll.top && touchPosition.y < rScroll.bottom; //withing y axis / height
        }
    }
}

package krishikalyan.aupadhyay.myapp.navdrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import krishikalyan.aupadhyay.myapp.R;
import krishikalyan.aupadhyay.myapp.navdrawer.core.AmitStyleDrawer;
import krishikalyan.aupadhyay.myapp.navdrawer.core.MenuFragment;

/**
 * Created by aupadhyay on 3/25/17.
 */

public class MyMenuFragment extends MenuFragment {

    private ImageView ivMenuUserProfilePhoto;
    public NavigationView navigationView;
    private AmitStyleDrawer mLeftDrawerLayout;
    AmitCallbackWhenDrawerOpens amitCallbackWhenDrawerOpens;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        amitCallbackWhenDrawerOpens = (AmitCallbackWhenDrawerOpens) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_renamed, container,
                false);

        View view1 = inflater.inflate(R.layout.activity_scrolling, container,
                false);
        mLeftDrawerLayout = (AmitStyleDrawer) view1.findViewById(R.id.id_drawerlayout);

        navigationView = (NavigationView) view.findViewById(R.id.vNavigation);

        //View navHeader = navigationView.getHeaderView(0); using when we are using support library greater than 23.0.0
        ivMenuUserProfilePhoto = (ImageView) navigationView.findViewById(R.id.ivMenuUserProfilePhoto);
        //setupHeader();

        return  setupReveal(view) ;
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);


    }

    public void onOpenMenu(){
        if (amitCallbackWhenDrawerOpens!= null)
            amitCallbackWhenDrawerOpens.drawerIsOpen();
    }
    public void onCloseMenu(){
    }

    public interface AmitCallbackWhenDrawerOpens {
        void drawerIsOpen();
    }
}


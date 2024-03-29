package com.example.appsale29032022.presentation.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appsale29032022.presentation.view.fragment.onboard.EatHealthyFragment;
import com.example.appsale29032022.presentation.view.fragment.onboard.HealthyRecipesFragment;

public class OnboardDingPagerAdapter extends FragmentStateAdapter{
    private Fragment eatHealthyFragment, healthyRecipesFragment;
    public OnboardDingPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                if (eatHealthyFragment==null){
                    eatHealthyFragment=new EatHealthyFragment();
                }
                return eatHealthyFragment;
            default:
                if(healthyRecipesFragment==null){
                    healthyRecipesFragment=new HealthyRecipesFragment();
                }
                return healthyRecipesFragment;
        }

    }
    @Override
    public int getItemCount() {
        return 2;
    }
}



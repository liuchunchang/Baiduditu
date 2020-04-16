package com.example.bishe_git;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;

import java.util.List;

public class Search {
    public SuggestionSearch mSuggestionSearch = SuggestionSearch.newInstance();
    public List<SuggestionResult.SuggestionInfo> resl=null;

    public  void search(String keyword) {
        /**
         * 在您的项目中，keyword为随您的输入变化的值
         */
        OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                //处理sug检索结果
//                    if (suggestionResult.getAllSuggestions() == null) {
//                        return;
//                    } else {
                        resl = suggestionResult.getAllSuggestions();
//                        return;
//                    }
            }
        };
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                .city("广东")
                .keyword(keyword));
    }
}

package com.example.sqlitepractice.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sqlitepractice.model.Emp;

public class EmpViewModel extends ViewModel {
    private MutableLiveData<Emp> emp;

    public MutableLiveData<Emp> getEmp() {
        if (emp == null) {
            emp = new MutableLiveData<>();
        }
        return emp;
    }

//    public String titleToString(){
//        switch (emp.getValue().getJobTitle()){
//            case 1:
//                return "部長";
//            case 2:
//                return "經理";
//            case 3:
//                return "員工";
//            default:
//                return "???";
//        }
//    }
}

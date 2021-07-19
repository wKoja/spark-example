package com.cbtest;

import com.cbtest.controller.AddressController;
import com.google.inject.AbstractModule;

public class Modules extends AbstractModule {

    @Override
    protected void configure(){
        //TODO: try implementing google guice
       bind(AddressController.class);
//       bind(AddressService.class).to(AddressServiceImpl.class);
    }
}

package com.appl_maint_mngt.views.account;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.account.IAccountController;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.forms.account.RegisterForm;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.repositories.account.IAccountObserverUpdateTypes;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.maintenance_engineer.IMaintenanceEngineerObserverUpdateTypes;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantObserverUpdateTypes;
import com.appl_maint_mngt.validation.account.IRegisterFormValidator;
import com.appl_maint_mngt.validation.account.RegisterFormValidator;
import com.appl_maint_mngt.validation.common.IValidatorResponse;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.common.GenericListViewDialogBuilder;
import com.appl_maint_mngt.views.maintenance_organisation.MaintenanceOrganisationListAdapter;
import com.appl_maint_mngt.web.models.account.UserProfile;
import com.appl_maint_mngt.web.models.maintenance_engineer.MaintenanceEngineerPayload;
import com.appl_maint_mngt.web.models.property_manager.PropertyManagerPayload;
import com.appl_maint_mngt.web.models.property_tenant.PropertyTenantPayload;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AccountRegisterActivity extends AppCompatActivity implements Observer {

    private IAccountController controller;

    private EditText emailField;
    private EditText passwordField;
    private EditText passwordConfField;
    private EditText firstNameField;
    private EditText surnameField;

    private DateTime selectedDateTime;
    private String selectedUserType;

    private IRegisterFormValidator registerFormValidator;
    private IAccountReadableRepository repository;

    private RegisterForm validForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);
        Button selectDOBBtn = (Button) findViewById(R.id.register_button_selectdate);

        setupFields();
        registerFormValidator = new RegisterFormValidator();

        controller = ControllerFactory.getInstance().getAccountController();
        repository = RepositoryFactory.getInstance().getReadableAccountRepository();

        RepositoryFactory.getInstance().observeAccountRepository(this);

        Spinner userTypeSelection = (Spinner) findViewById(R.id.register_spinner_usertype);
        userTypeSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUserType =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        UserType[] types = UserType.values();
        List<String> typesList = new ArrayList<>();
        for(UserType type: types) {
            typesList.add(type.toString());
        }

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typesList);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSelection.setAdapter(userTypeAdapter);

        final TextView dobTv = (TextView) findViewById(R.id.register_textview_date_display);

        Calendar calendar = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(i, i1, i2);
                selectedDateTime = new DateTime(newDate);
                dobTv.setText(selectedDateTime.toString());
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        selectDOBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        Button registerBtn = (Button) findViewById(R.id.register_button_submit);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterForm form = new RegisterForm();
                form.setEmail(emailField.getEditableText().toString());
                if(selectedDateTime != null) form.setDateOfBirth(new Timestamp(selectedDateTime.getMillis()));
                form.setFirstName(firstNameField.getEditableText().toString());
                form.setSurname(surnameField.getEditableText().toString());
                form.setPassword(passwordField.getEditableText().toString());
                form.setPasswordConfirmation(passwordConfField.getEditableText().toString());
                if(selectedUserType != null) form.setUserType(selectedUserType);

                IValidatorResponse response = registerFormValidator.validate(form);
                if(response.isValid()) {
                    validForm = form;
                    controller.createAccount(form, new IErrorCallback() {
                        @Override
                        public void callback(ErrorPayload payload) {
                            new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
                        }
                    });
                } else {
                    new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, response.getErrors()).show();
                }
            }
        });
    }

    private void setupFields() {
        emailField = (EditText) findViewById(R.id.register_edittext_email);
        passwordField = (EditText) findViewById(R.id.register_edittext_password);
        passwordConfField = (EditText) findViewById(R.id.register_edittext_password_conf);
        firstNameField = (EditText) findViewById(R.id.register_edittext_firstname);
        surnameField = (EditText) findViewById(R.id.register_edittext_surname);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IAccountObserverUpdateTypes.TOKEN_UPDATE)){
                controller.getAuthDetails(repository.get().getToken(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
                    }
                });
            } else if(o.equals(IAccountObserverUpdateTypes.AUTH_UPDATE)) {
                UserProfile profile = new UserProfile();
                profile.setFirstName(validForm.getFirstName());
                profile.setSurname(validForm.getSurname());
                profile.setDateOfBirth(validForm.getDateOfBirth());
                profile.setAccountId(repository.get().getId());
                controller.createProfile(profile, new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
                    }
                });
            } else if(o.equals(IAccountObserverUpdateTypes.PROFILE_UPDATE)) {
                IAccountReadable account = repository.get();
                switch(account.getUserType()) {
                    case PROPERTY_MANAGER:
                        createPropertyManager(account);
                        break;
                    case PROPERTY_TENANT:
                        createPropertyTenant(account);
                        break;
                    case MAINTENANCE_ENGINEER:
                        getOrganisations();
                        break;
                }
            } else if(o.equals(IPropertyManagerObserverUpdateTypes.MODEL_UPDATE) || o.equals(IPropertyTenantObserverUpdateTypes.MODEL_UPDATE)) {
                toLoginView();
            } else if(o.equals(IMaintenanceOrganisationObserverUpdateTypes.MODEL_UPDATE)) {
                triggerOrganisationDialog();
            } else if(o.equals(IMaintenanceEngineerObserverUpdateTypes.MODEL_UPDATE)) {
                toLoginView();
            }
        }
    }

    private void createPropertyManager(IAccountReadable account) {
        RepositoryFactory.getInstance().observePropertyManagerRepository(this);
        PropertyManagerPayload payload = new PropertyManagerPayload();
        payload.setAccountId(account.getId());
        ControllerFactory.getInstance().getPropertyManagerController().createPropertyManager(payload, new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
            }
        });
    }

    private void createPropertyTenant(IAccountReadable account) {
        RepositoryFactory.getInstance().observerPropertyTenantRepository(this);
        PropertyTenantPayload payload = new PropertyTenantPayload();
        payload.setAccountId(account.getId());
        ControllerFactory.getInstance().getPropertyTenantController().createPropertyTenant(payload, new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
            }
        });
    }

    private void getOrganisations() {
        RepositoryFactory.getInstance().observerMaintenanceOrganisationRepository(this);
        ControllerFactory.getInstance().getMaintenanceOrganisationController().getAll(new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
            }
        });
    }

    private void triggerOrganisationDialog() {
        List<IMaintenanceOrganisationReadable> organisations = RepositoryFactory.getInstance().getReadableMaintenanceOrganisationRepository().getAll();
        MaintenanceOrganisationListAdapter adapter = new MaintenanceOrganisationListAdapter(this, organisations);
        AlertDialog dialog = new GenericListViewDialogBuilder().build(this, adapter, R.string.register_eng_select_org, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IMaintenanceOrganisationReadable org = (IMaintenanceOrganisationReadable) adapterView.getItemAtPosition(i);
                MaintenanceEngineerPayload payload = new MaintenanceEngineerPayload();
                payload.setAccountId(repository.get().getId());
                payload.setCurrentOrganisationId(org.getId());
                RepositoryFactory.getInstance().observerMaintenanceEngineerRepository(AccountRegisterActivity.this);
                ControllerFactory.getInstance().getMaintenanceEngineerController().create(payload, new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(AccountRegisterActivity.this, payload.getErrors()).show();
                    }
                });
            }
        });
        dialog.show();
    }

    private void toLoginView() {
        Intent loginView = new Intent(this, LoginActivity.class);
        startActivity(loginView);
    }
}

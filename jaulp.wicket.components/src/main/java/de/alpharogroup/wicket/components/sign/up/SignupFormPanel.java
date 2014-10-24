package de.alpharogroup.wicket.components.sign.up;

import net.sourceforge.jaulp.auth.models.BaseUsernameSignUpModel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

public abstract class SignupFormPanel extends
		BasePanel<BaseUsernameSignUpModel> {
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private final Label buttonLabel;

	private final Button submitButton;

	private final Form<BaseUsernameSignUpModel> form;
	
	private final Component signupPanel;

	public SignupFormPanel(String id) {
		super(id);
		BaseUsernameSignUpModel modelObject = new BaseUsernameSignUpModel();
		modelObject.setEmail("");
		IModel<BaseUsernameSignUpModel> model = new CompoundPropertyModel<BaseUsernameSignUpModel>(
				modelObject);
		setModel(model);
		addOrReplace(form = newForm("form", model));
		form.addOrReplace(signupPanel = newSignupPanel("signupPanel", getModel()));
		
		form.addOrReplace(submitButton = newButton("signupButton"));
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel",
				"global.button.sign.up.label", "Sign up", this));
		form.add(submitButton);
	}

	/**
	 * Factory method for creating the SignupPanel. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a SignupPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the SignupPanel
	 */
	protected Component newSignupPanel(String id,IModel<BaseUsernameSignUpModel> model) {
				return new SignupPanel(id, model);		
	}

	/**
	 * New form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<BaseUsernameSignUpModel> newForm(String id,
			IModel<BaseUsernameSignUpModel> model) {
		return new Form<BaseUsernameSignUpModel>(id, model);
	}

	/**
	 * New button.
	 * 
	 * @param id
	 *            the id
	 * @return the component
	 */
	protected Button newButton(String id) {
		return new Button(id) {
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSignup();
			}
		};
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newButtonLabel(String id, final String resourceKey,
			final String defaultValue, final Component component) {
		final IModel<String> labelModel = ResourceModelFactory
				.newResourceModel(resourceKey, component, defaultValue);
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	protected abstract void onSignup();

	public Component getSignupPanel() {
		return signupPanel;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public Label getButtonLabel() {
		return buttonLabel;
	}

	public Form<BaseUsernameSignUpModel> getForm() {
		return form;
	}
}
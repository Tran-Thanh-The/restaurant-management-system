package controllers.popup;

import dao.FoodCategoryDao;
import dao.FoodItemDao;
import java.awt.event.ActionListener;
import models.FoodCategory;
import models.FoodItem;
import views.popup.FoodItemPopupView;

/**
 * Tran Thanh The
 */
public class FoodItemPopupController {

    FoodItemDao foodItemDao = new FoodItemDao();
    FoodCategoryDao foodCategoryDao = new FoodCategoryDao();

    private void initComboBox(FoodItemPopupView view) { //Khởi tạo danh mục loai mon
        try {
            for (FoodCategory foodCategory : foodCategoryDao.getAll()) {
                view.getFoodCategoryComboBoxModel().addElement(foodCategory);
            }
        } catch (Exception e) {
        }
    }

    public void add(FoodItemPopupView view, SuccessCallback sc, ErrorCallback ec) {
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        initComboBox(view);
        view.getBtnOK().addActionListener(evt -> {
            try {
                addFoodItem(view);
                view.dispose();
                view.showMessage("Thêm món ăn thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public void edit(FoodItemPopupView view, FoodItem foodItem, SuccessCallback sc, ErrorCallback ec) {
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        initComboBox(view);
        view.getLbTitle().setText("Sửa món ăn - " + foodItem.getId());
        view.getBtnOK().setText("Cập nhật");

        if (foodItem == null) {
            view.showError("Món không tồn tại");
        }
        view.getTxtName().setText(foodItem.getName());
        view.getTxtDescription().setText(foodItem.getDescription());
        view.getTxtUrlImage().setText(foodItem.getUrlImage());
        view.getTxtUnitName().setText(foodItem.getUnitName());
        view.getTxtUnitPrice().setText(foodItem.getUnitPrice() + "");

        FoodCategory fc = new FoodCategory();
        fc.setId(foodItem.getIdCategory());
        view.getCboCategory().setSelectedItem(fc);
        view.getBtnOK().addActionListener(evt -> {
            try {
                editFoodItem(view, foodItem);
                view.dispose();
                view.showMessage("Sửa món ăn thành công!");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });

    }

    public boolean addFoodItem(FoodItemPopupView view) throws Exception {
        FoodItem foodItem = new FoodItem();
        FoodCategory selectCategory = (FoodCategory) view.getCboCategory().getSelectedItem();
        String name = view.getTxtName().getText(), unitName = view.getTxtUnitName().getText(),
                unitPrice = view.getTxtUnitPrice().getText(), urlImage = view.getTxtUrlImage().getText(),
                description = view.getTxtDescription().getText();
        if (name.isEmpty() || unitName.isEmpty() || unitPrice.isEmpty() || selectCategory == null) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        foodItem.setName(name);
        foodItem.setDescription(unitName);
        foodItem.setUnitName(unitName);
        foodItem.setUnitPrice(Integer.parseInt(unitPrice));
        foodItem.setUrlImage(urlImage);
        foodItem.setDescription(description);
        foodItem.setIdCategory(selectCategory.getId());
        foodItemDao.save(foodItem);
        return true;
    }

    public boolean editFoodItem(FoodItemPopupView view, FoodItem foodItem) throws Exception {
        FoodCategory selectCategory = (FoodCategory) view.getCboCategory().getSelectedItem();
        String name = view.getTxtName().getText(), unitName = view.getTxtUnitName().getText(),
                unitPrice = view.getTxtUnitPrice().getText(), urlImage = view.getTxtUrlImage().getText(),
                description = view.getTxtDescription().getText();
        if (name.isEmpty() || unitName.isEmpty() || unitPrice.isEmpty() || selectCategory == null) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        foodItem.setName(name);
        foodItem.setDescription(description);
        foodItem.setUnitName(unitName);
        foodItem.setUnitPrice(Integer.parseInt(unitPrice));
        foodItem.setUrlImage(urlImage);
        foodItem.setDescription(description);
        foodItem.setIdCategory(selectCategory.getId());
        foodItemDao.update(foodItem);
        return true;
    }
}

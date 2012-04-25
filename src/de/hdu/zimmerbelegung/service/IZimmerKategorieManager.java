package de.hdu.zimmerbelegung.service;

import java.util.List;
import de.hdu.zimmerbelegung.model.ZimmerKategorie;

public interface IZimmerKategorieManager {

public abstract void delete(ZimmerKategorie zimmerKategorie);

public abstract void update(ZimmerKategorie zimmerKategorie);

public abstract void add(ZimmerKategorie zimmerKategorie);

public abstract List<ZimmerKategorie> getAll();

}
using System.Collections.Generic;
using System.Linq;

using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.DL.DataLayerContract;
using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.BL.BusinessLayerImpl
{
    public class ProductOperations : IProductOperations
    {
        private IDataAccess<Product> _productDataAccess;
        private IDataAccess<Category> _categoryDataAccess;

        public ProductOperations(IDataAccess<Product> productDataAccess,
                                 IDataAccess<Category> categoryDataAccess)
        {
            _productDataAccess = productDataAccess;
            _categoryDataAccess = categoryDataAccess;
        }

        public void Create(ProductDTO product)
        {
            var category = _categoryDataAccess.Read().
                Single(x => x.Id == product.CategoryId);

            _productDataAccess.Add(new Product() 
            { 
                Name = product.Name,
                Price = product.Price,
                Category = category
            });
        }

        public IEnumerable<ProductDTO> Get()
        {
            return _productDataAccess.Read().
                Select(x => new ProductDTO() 
                {
                    CategoryId = x.Category.Id,
                    Id = x.Id,
                    Price = x.Price,
                    Name = x.Name
                });
        }
    }
}

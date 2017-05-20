using System;
using System.Collections.Generic;
using System.Linq;

using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.DL.DataLayerContract;
using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.BL.BusinessLayerImpl
{
    public class CategoryOperations : ICategoryOperastions
    {
        private IDataAccess<Category> _categoryDataAccess;

        public CategoryOperations(IDataAccess<Category> categoryDataAccess)
        {
            _categoryDataAccess = categoryDataAccess;
        }

        public void Create(CategoryDTO category)
        {
            _categoryDataAccess.Add(new Category()
            {
                Name = category.Name
            });
        }

        public IEnumerable<CategoryDTO> Get()
        {
            return _categoryDataAccess.Read().
                Select(x => new CategoryDTO()
                {
                    Id = x.Id,
                    Name = x.Name
                });
        }
    }
}
